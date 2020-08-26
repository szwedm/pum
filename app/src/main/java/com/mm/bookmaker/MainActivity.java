package com.mm.bookmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mm.bookmaker.database.AppDatabase;
import com.mm.bookmaker.models.Team;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Team> team= new ArrayList<>();
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        team.add(new Team(1,"Legia Warszawa",37, 21,6,10));
        team.add(new Team(2,"Lech Poznan",37, 18,12,7));
        team.add(new Team(3,"Piast Gliwice",37, 18,7,12));
        team.add(new Team(4,"Lechia Gdansk",37, 15,7,12));
        team.add(new Team(5,"Slask Wroclaw",37, 14,7,12));
        team.add(new Team(6,"Pogon Szczecin",37, 14,7,12));
        team.add(new Team(7,"Cracovia",37, 16,7,12));
        team.add(new Team(8,"Jagiellonia Bialystok",37, 14,7,12));
        team.add(new Team(9,"Gornik Zabrze",37, 14,7,12));
        team.add(new Team(10,"Rakow Czestochowa",37, 16,7,12));
        team.add(new Team(11,"KGHM Zaglebie Lubin",37, 15,7,12));
        team.add(new Team(12,"Wisla Plock",37, 14,7,12));
        team.add(new Team(13,"Wisla Krakow",37, 13,7,12));
        team.add(new Team(14,"Arka Gdynia",37, 10,10,17));
        team.add(new Team(15,"Korona Kielce",37, 9,8,20));
        team.add(new Team(16,"LKS Lodz",37, 6,6,25));



        db = AppDatabase.getInstance(getApplicationContext());
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
        }
        return super.onOptionsItemSelected(item);
    }

}