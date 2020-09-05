package com.mm.bookmaker;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

        TableLayout stk = (TableLayout) findViewById(R.id.league_table);
        ArrayList<Team> teams = new ArrayList<>(db.teamDao().getAllTeamsByPoints());

        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText( "#");
        tv0.setTextColor(Color.BLACK);
        tv0.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv0.setPadding(10,0,10,0);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText("Klub");
        tv1.setTextColor(Color.BLACK);
        tv1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv1.setPadding(10,0,10,0);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText("W");
        tv2.setTextColor(Color.BLACK);
        tv2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv2.setPadding(10,0,10,0);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText("R");
        tv3.setTextColor(Color.BLACK);
        tv3.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv3.setPadding(10,0,10,0);
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText("P");
        tv4.setTextColor(Color.BLACK);
        tv4.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv4.setPadding(10,0,10,0);
        tbrow0.addView(tv4);
        TextView tv5 = new TextView(this);
        tv5.setText("PKT");
        tv5.setTextColor(Color.BLACK);
        tv5.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv5.setPadding(10,0,10,0);
        tbrow0.addView(tv5);
        stk.addView(tbrow0);

        for(int i =0;i<teams.size();i++){
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            TextView t2v = new TextView(this);
            TextView t3v = new TextView(this);
            TextView t4v = new TextView(this);
            TextView t5v = new TextView(this);
            TextView t6v = new TextView(this);
            if(i==0){
                t1v.setTextColor(Color.BLUE);
                t2v.setTextColor(Color.BLUE);
                t3v.setTextColor(Color.BLUE);
                t4v.setTextColor(Color.BLUE);
                t5v.setTextColor(Color.BLUE);
                t6v.setTextColor(Color.BLUE);
            }
            if(i==15){
                t1v.setTextColor(Color.RED);
                t2v.setTextColor(Color.RED);
                t3v.setTextColor(Color.RED);
                t4v.setTextColor(Color.RED);
                t5v.setTextColor(Color.RED);
                t6v.setTextColor(Color.RED);
            }
            String number= String.valueOf(i+1);
            t1v.setText(number);
            t1v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t1v.setPadding(10,0,10,0);
            tbrow.addView(t1v);

            t2v.setText(teams.get(i).getName());
            t2v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t2v.setPadding(10,0,10,0);
            tbrow.addView(t2v);

            String wins= String.valueOf(teams.get(i).getWins());
            t3v.setText("  "+wins);
            t3v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t3v.setPadding(10,0,10,0);
            tbrow.addView(t3v);

            String draws= String.valueOf(teams.get(i).getDraws());
            t4v.setText(draws);
            t4v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t4v.setPadding(10,0,10,0);
            tbrow.addView(t4v);

            String loses= String.valueOf(teams.get(i).getLoses());
            t5v.setText(loses);
            t5v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t5v.setPadding(10,0,10,0);
            tbrow.addView(t5v);

            String points= String.valueOf(teams.get(i).getPoints());
            t6v.setText(points);
            t6v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t6v.setPadding(10,0,10,0);
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
