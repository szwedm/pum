package com.mm.bookmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.mm.bookmaker.api.ApiCallback;
import com.mm.bookmaker.api.ApiServiceGenerator;
import com.mm.bookmaker.database.AppDatabase;
import com.mm.bookmaker.extractors.StandingsResponseToModels;
import com.mm.bookmaker.models.Team;
import com.mm.bookmaker.models.retrofit.StandingsResponse;
import com.mm.bookmaker.services.MatchService;
import com.mm.bookmaker.services.PlayerService;
import com.mm.bookmaker.services.TeamService;

import java.util.ArrayList;

import retrofit2.Call;

public class MainActivity extends AppCompatActivity {

    private final AppDatabase db = AppDatabase.getInstance(getApplicationContext());

    private TeamService teamService;
    private PlayerService playerService;
    private MatchService matchService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        }
        if (item.getItemId() == R.id.calendar_item) {
            Intent intent = new Intent(this, CalendarActivity.class);
            startActivityForResult(intent,1);

        }
        if (item.getItemId() == R.id.topScorers_item) {
            Intent intent = new Intent(this, TopScorersActivity.class);
            startActivityForResult(intent,1);

        }
        if (item.getItemId() == R.id.refreshData_item) {
            // HTTP GET to retrieve new data from API
            // JSON parsing
            // DB update
            ApiCallback<StandingsResponse> standingsResponse = new ApiCallback<>();
            ArrayList<Team> teams = new ArrayList<>();

            teamService = ApiServiceGenerator.createService(TeamService.class);
            Call<StandingsResponse> callStandings = teamService.getTeamsAndStandings();
            callStandings.enqueue(standingsResponse);
            StandingsResponseToModels.extractFromResponse(
                    standingsResponse.getResponse(),
                    teams);

            for (Team team : teams) {
                db.teamDao().insertTeam(team);
            }
        }
        return super.onOptionsItemSelected(item);
    }

}