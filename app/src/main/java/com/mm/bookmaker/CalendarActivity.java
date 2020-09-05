package com.mm.bookmaker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mm.bookmaker.database.AppDatabase;
import com.mm.bookmaker.models.Match;

import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity {
    private AppDatabase db;
    CalendarView calendarView;
    TextView textView;

    private  static final String TAG = "CalendarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        TableLayout stk = (TableLayout) findViewById(R.id.calendar_table);
        textView =findViewById(R.id.textView4);



        calendarView = findViewById(R.id.calendarView);
        db = AppDatabase.getInstance(getApplicationContext());
        db.matchDao().getAll();


        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText("Druzyna #1         ");
        tv0.setTextColor(Color.BLACK);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText("Druzyna #2         ");
        tv1.setTextColor(Color.BLACK);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText("  Status");
        tv2.setTextColor(Color.BLACK);
        tbrow0.addView(tv2);
        stk.addView(tbrow0);


        ArrayList<Match> match = new ArrayList<>(db.matchDao().getAll());


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView CalendarView, int year, int month, int dayOfMonth) {
               // textView.setText(Long.toString(calendarView.getDate()));
                String date = year + "-" + (month+1) + "-"+ dayOfMonth ;
                Log.d(TAG, "onSelectedDayChange: yyyy-mm-dd:" + date);


                /*
                for (int i =0;i<match.size();i++){

                    String longDate = match.get(i).getDate();
                    String shortDate = longDate.substring(0, 10);
                    if(shortDate.equals(date)){

                        // Porownac timestamp z kalendarza z baza danych i potem wyswietlic



                        // textView.setText(String.format(new Locale("PL"),"%s vs %s",match.get(i).getHomeTeamName(), match.get(i).getAwayTeamName()));
                    }
                    if(!shortDate.equals(date)) {
                        //textView.setText(String.format(new Locale("PL"),"%s",calendarView.getDate()));

                        // textView.setText(String.format(new Locale("PL"),"%s vs %s",date, shortDate));
                    }

                }

                */
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
