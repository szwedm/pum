package com.mm.bookmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    private TeamService teamService;
    private PlayerService playerService;
    private MatchService matchService;
    private TextView textView1;
    private List<Team> teams = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(getApplicationContext());

        textView1 = (TextView) findViewById(R.id.textView1);

        teamService = ApiServiceGenerator.createService(TeamService.class);
        matchService = ApiServiceGenerator.createService(MatchService.class);
        playerService = ApiServiceGenerator.createService(PlayerService.class);
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
        }
        return super.onOptionsItemSelected(item);
    }

    public void saveTopScorersFromAPI() {
        Call<TopScorersResponse> callTopScorers = playerService.getTopScorers();
        callTopScorers.enqueue(new Callback<TopScorersResponse>() {
            @Override
            public void onResponse(Call<TopScorersResponse> call, Response<TopScorersResponse> response) {
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
        db.close();
    }
}