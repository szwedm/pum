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
import com.mm.bookmaker.models.Bet;
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

    private int money;
    private AppDatabase db;
    private SharedPreferences sharedPref;
    private MatchArrayAdapter adapter;
    private TeamService teamService;
    private PlayerService playerService;
    private MatchService matchService;
    private List<Match> matches;
    private List<Bet> bets;
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

        if (db.matchDao().getIncoming8(System.currentTimeMillis()/1000).isEmpty()) {
            matches = new ArrayList<>();
        } else {
            matches = new ArrayList<>(db.matchDao().getIncoming8(System.currentTimeMillis()/1000));
        }

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
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.leagueTable_item) {
            Intent intent = new Intent(this, LeagueTableActivity.class);
            startActivity(intent);
        }  else if (item.getItemId() == R.id.topScorers_item) {
            Intent intent = new Intent(this, TopScorersActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.refreshData_item) {
            saveTeamsFromAPI();
            saveTopScorersFromAPI();
            saveMatchesFromAPI();
            updateMatchAdapter();
            updateBets();
        } else if (item.getItemId() == R.id.bets_item) {
            Intent intent = new Intent(this, BetsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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
    protected void onPause() {
        super.onPause();
        sharedPref.edit().putInt("savedMoney", money).commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sharedPref.edit().putInt("savedMoney", money).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sharedPref.edit().putInt("savedMoney", money).commit();
        db.close();
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

    private void updateMatchAdapter() {
        matches = new ArrayList<>(db.matchDao().getIncoming8(System.currentTimeMillis()/1000));
        if (!(matches.isEmpty())) {
            adapter.clear();
            adapter.addAll(matches);
            adapter.notifyDataSetChanged();
        }
    }

    private void updateBets() {
        bets = new ArrayList<>(db.betDao().getBetsByStatus("P"));
        for (Bet bet : bets) {
            Match match = db.matchDao().findById(bet.getMatch_id());
            if (match.getStatusShort().equals("FT")) {
                String scoreSymbol;
                int homeTeamGoals = match.getHomeTeamGoals();
                int awayTeamGoals = match.getAwayTeamGoals();
                if (homeTeamGoals > awayTeamGoals) scoreSymbol = "1";
                else if (homeTeamGoals == awayTeamGoals) scoreSymbol = "X";
                else scoreSymbol = "2";

                if (bet.getType().equals(scoreSymbol)) {
                    money += 2 * bet.getValue();
                    bet.setStatus("W");
                } else {
                    bet.setStatus("L");
                }
                db.betDao().updateBet(bet);
                mainTextView.setText(money + " PLN");
            }
        }
    }
}