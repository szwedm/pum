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
import com.mm.bookmaker.extractors.StandingsResponseToModels;
import com.mm.bookmaker.models.Team;
import com.mm.bookmaker.models.retrofit.StandingsResponse;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = AppDatabase.getInstance(getApplicationContext());

        textView1 = (TextView) findViewById(R.id.textView1);

        teamService = ApiServiceGenerator.createService(TeamService.class);
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
            // HTTP GET to retrieve new data from API
            // JSON parsing
            // DB update

            Call<StandingsResponse> callStandings = teamService.getTeamsAndStandings();
            callStandings.enqueue(new Callback<StandingsResponse>() {
                @Override
                public void onResponse(Call<StandingsResponse> call, Response<StandingsResponse> response) {
                    StandingsResponse standingsResponse = response.body();
                    StandingsResponseToModels.extractFromResponse(standingsResponse, teams);
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
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}