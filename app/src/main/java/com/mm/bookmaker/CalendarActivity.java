package com.mm.bookmaker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mm.bookmaker.database.AppDatabase;
import com.mm.bookmaker.models.Match;

import java.util.ArrayList;
import java.util.Locale;

public class CalendarActivity extends AppCompatActivity {
    private AppDatabase db;
    CalendarView calendarView;
    private  static final String TAG = "CalendarActivity";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        textView = findViewById(R.id.testmatch);
        calendarView = findViewById(R.id.calendarView);
        db = AppDatabase.getInstance(getApplicationContext());
        db.matchDao().getAll();

        ArrayList<Match> match = new ArrayList<>(db.matchDao().getAll());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int dayOfMonth) {
                String date = year + "-" + month + "-"+ dayOfMonth ;
                Log.d(TAG, "onSelectedDayChange: yyyy-mm-dd:" + date);
                for (int i =0;i<match.size();i++){

                    String shortDate = match.get(i).getDate();
                    shortDate.substring(0,10);

                    if(!date.equals(shortDate)) {
                        textView.setText(String.format(new Locale("PL"), "BRAK MECZU w dniu %s a %s",date, shortDate));
                    }
                }

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
            startActivityForResult(intent,1);

        }
        if (item.getItemId() == R.id.calendar_item) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivityForResult(intent,1);

        }
        if (item.getItemId() == R.id.topScorers_item) {
            Intent intent = new Intent(this, LeagueTableActivity.class);
            startActivityForResult(intent,1);

        }
        return super.onOptionsItemSelected(item);
    }
}
