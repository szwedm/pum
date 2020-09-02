package com.mm.bookmaker;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mm.bookmaker.database.AppDatabase;
import com.mm.bookmaker.models.Team;

import java.util.ArrayList;

public class LeagueTableActivity extends AppCompatActivity {

    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaguetable);

        db = AppDatabase.getInstance(getApplicationContext());
        db.playerDao().getAllSortByGoals();

        TableLayout stk = (TableLayout) findViewById(R.id.table_layout);
        ArrayList<Team> teams = new ArrayList<>(db.teamDao().getAllTeamsByPoints());

        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" #         ");
        tv0.setTextColor(Color.BLACK);

        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText("Klub               ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText("  W          ");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText("R          ");
        tv3.setTextColor(Color.BLACK);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText("P          ");
        tv4.setTextColor(Color.BLACK);
        tbrow0.addView(tv4);
        TextView tv5 = new TextView(this);
        tv5.setText("PKT        ");
        tv5.setTextColor(Color.BLACK);
        tbrow0.addView(tv5);
        stk.addView(tbrow0);

        for(int i =0;i<teams.size();i++){
            TableRow tbrow = new TableRow(this);

            TextView t1v = new TextView(this);
            String number= String.valueOf(i+1);
            t1v.setText(number);
            tbrow.addView(t1v);

            TextView t2v = new TextView(this);
            t2v.setText(teams.get(i).getName());
            tbrow.addView(t2v);

            TextView t3v= new TextView(this);
            String wins= String.valueOf(teams.get(i).getWins());
            t3v.setText("  "+wins);
            tbrow.addView(t3v);

            TextView t4v = new TextView(this);
            String draws= String.valueOf(teams.get(i).getDraws());
            t4v.setText(draws);
            tbrow.addView(t4v);

            TextView t5v = new TextView(this);
            String loses= String.valueOf(teams.get(i).getLoses());
            t5v.setText(loses);
            tbrow.addView(t5v);

            TextView t6v = new TextView(this);
            String points= String.valueOf(teams.get(i).getPoints());
            t6v.setText(points);
            tbrow.addView(t6v);

            stk.addView(tbrow);
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
            Intent intent = new Intent(this, MainActivity.class);
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
        return super.onOptionsItemSelected(item);
    }
}
