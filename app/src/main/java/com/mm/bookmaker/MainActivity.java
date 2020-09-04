package com.mm.bookmaker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mm.bookmaker.adapters.MatchArrayAdapter;
import com.mm.bookmaker.api.ApiServiceGenerator;
import com.mm.bookmaker.database.AppDatabase;
import com.mm.bookmaker.extractors.ResponseToModel;
import com.mm.bookmaker.models.Match;
import com.mm.bookmaker.models.Player;
import com.mm.bookmaker.models.Team;
import com.mm.bookmaker.models.retrofit.MatchesResponse;
import com.mm.bookmaker.models.retrofit.StandingsResponse;
import com.mm.bookmaker.models.retrofit.TopScorersResponse;
import com.mm.bookmaker.services.MatchService;
import com.mm.bookmaker.services.PlayerService;
import com.mm.bookmaker.services.TeamService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private SharedPreferences sharedPref;
    private MatchArrayAdapter adapter;
    private int money;
    private TeamService teamService;
    private PlayerService playerService;
    private MatchService matchService;
    private ArrayList<Match> matches;
    private ListView mainListView;
    private TextView mainTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(getApplicationContext());
        sharedPref = getPreferences(MODE_PRIVATE);

        teamService = ApiServiceGenerator.createService(TeamService.class);
        matchService = ApiServiceGenerator.createService(MatchService.class);
        playerService = ApiServiceGenerator.createService(PlayerService.class);

        mainListView = (ListView) findViewById(R.id.main_listView);
        matches = new ArrayList<>(db.matchDao().getIncoming8(System.currentTimeMillis()/1000));
        adapter = new MatchArrayAdapter(getApplicationContext(), matches);
        mainListView.setAdapter(adapter);

        mainTextView = (TextView) findViewById(R.id.main_textView);
        money = sharedPref.getInt("savedMoney", 1000);

        mainTextView.setText(money + " PLN");

        mainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), BetActivity.class);
                intent.putExtra("money", money);
                intent.putExtra("match", matches.get(position));
                startActivityForResult(intent, 200);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == RESULT_OK) {
                money -= data.getIntExtra("betValue", 0);
                mainTextView.setText(money + " PLN");
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.leagueTable_item) {
            Intent intent = new Intent(this, LeagueTableActivity.class);
            startActivityForResult(intent,1);
        } else if (item.getItemId() == R.id.calendar_item) {
            Intent intent = new Intent(this, CalendarActivity.class);
            startActivityForResult(intent,1);
        } else if (item.getItemId() == R.id.topScorers_item) {
            Intent intent = new Intent(this, TopScorersActivity.class);
            startActivityForResult(intent,1);
        } else if (item.getItemId() == R.id.refreshData_item) {
            saveTeamsFromAPI();
            saveTopScorersFromAPI();
            saveMatchesFromAPI();
            matches = new ArrayList<>(db.matchDao().getIncoming8(System.currentTimeMillis()/1000));
            if (!(matches.isEmpty())) {
                adapter.clear();
                adapter.addAll(matches);
                adapter.notifyDataSetChanged();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveTopScorersFromAPI() {
        Call<TopScorersResponse> callTopScorers = playerService.getTopScorers();
        callTopScorers.enqueue(new Callback<TopScorersResponse>() {
            @Override
            public void onResponse(Call<TopScorersResponse> call, Response<TopScorersResponse> response) {
                List<Player> players = new ArrayList<>();
                TopScorersResponse topScorersResponse = response.body();
                ResponseToModel.extractTopScorersFromTopScorersResponse(topScorersResponse, players);
                for (Player player : players) {
                    db.playerDao().insertPlayer(player);
                }
            }

            @Override
            public void onFailure(Call<TopScorersResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: TopScorers download failed!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void saveMatchesFromAPI() {
        Call<MatchesResponse> callMatches = matchService.getMatches();
        callMatches.enqueue(new Callback<MatchesResponse>() {
            @Override
            public void onResponse(Call<MatchesResponse> call, Response<MatchesResponse> response) {
                List<Match> matches = new ArrayList<>();
                MatchesResponse matchesResponse = response.body();
                ResponseToModel.extractMatchesFromMatchesResponse(matchesResponse, matches);
                for (Match match : matches) {
                    db.matchDao().insertMatch(match);
                }
            }

            @Override
            public void onFailure(Call<MatchesResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: Matches download failed!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void saveTeamsFromAPI() {
        Call<StandingsResponse> callStandings = teamService.getTeamsAndStandings();
        callStandings.enqueue(new Callback<StandingsResponse>() {
            @Override
            public void onResponse(Call<StandingsResponse> call, Response<StandingsResponse> response) {
                List<Team> teams = new ArrayList<>();
                StandingsResponse standingsResponse = response.body();
                ResponseToModel.extractTeamsFromStandingsResponse(standingsResponse, teams);
                for (Team team : teams) {
                    db.teamDao().insertTeam(team);
                }
            }

            @Override
            public void onFailure(Call<StandingsResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error: Standings download failed!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPref.edit().putInt("savedMoney", money).commit();
        db.close();
    }
}