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
import com.mm.bookmaker.models.Player;

import java.util.ArrayList;

public class TopScorersActivity extends AppCompatActivity {

    private AppDatabase db;

    //List<Player> players = new ArrayList<>(db.playerDao().getAllSortByGoals());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topscorers);
        //TableLayout tableLayout = findViewById(R.id.table_layout);
        db = AppDatabase.getInstance(getApplicationContext());
        db.playerDao().getAllSortByGoals();
        TableLayout stk = (TableLayout) findViewById(R.id.topScorers_table);
        ArrayList<Player> players = new ArrayList<>(db.playerDao().getAllSortByGoals());

        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" #     ");
        tv0.setTextColor(Color.BLACK);

        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText("Imię i Nazwisko    ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText("  Klub");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText("Bramki");
        tv3.setTextColor(Color.BLACK);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);

        for(int i =0;i<players.size();i++){
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            String number= String.valueOf(i+1);
            t1v.setText(number);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(players.get(i).getFullName());
            tbrow.addView(t2v);
            TextView t3v= new TextView(this);
            t3v.setText("  "+players.get(i).getTeamName());
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            String goals= String.valueOf(players.get(i).getGoals());
            t4v.setText(goals);
            tbrow.addView(t4v);
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
            Intent intent = new Intent(this, LeagueTableActivity.class);
            startActivityForResult(intent,1);

        }
        if (item.getItemId() == R.id.calendar_item) {
            Intent intent = new Intent(this, CalendarActivity.class);
            startActivityForResult(intent,1);

        }
        if (item.getItemId() == R.id.topScorers_item) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent,1);

        }
        return super.onOptionsItemSelected(item);
    }
}