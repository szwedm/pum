package com.mm.bookmaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.mm.bookmaker.database.AppDatabase;
import com.mm.bookmaker.models.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    List<Team> team= new ArrayList<>();
    TextView moneyToSpend;
    CheckBox win1;
    EditText moneyToBet;
    CheckBox draw;
    CheckBox win2;
    Button betB;
    float money = 500;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        moneyToSpend= findViewById(R.id.moneyToSpendTV);
        moneyToBet= findViewById(R.id.moneyToBet);
        betB= findViewById(R.id.betB);
        win1= findViewById(R.id.winFirstTeamCB);
        draw= findViewById(R.id.drawCB);
        win2= findViewById(R.id.winSecondTeamCB);

        team.add(new Team(1,"Legia Warszawa",37, 21,6,10));
        team.add(new Team(2,"Lech Poznan",37, 18,7,12));
        team.add(new Team(3,"Piast Gliwice",37, 18,12,7));
        team.add(new Team(4,"Lechia Gdansk",37, 15,11,12));
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
        moneyToSpend.setText(String.format(new Locale("PL"),"%.2f $",money));
        //money();


        db = AppDatabase.getInstance(getApplicationContext());
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.winFirstTeamCB:
                if (checked){

                }

            else

                break;
            case R.id.drawCB:
                if (checked){

                }

            else

                break;
            case R.id.winSecondTeamCB:
                if (checked){

                }

            else

                break;
            // TODO: Veggie sandwich
        }
    }
    public void setMoneyToBet(View view){
        float result;
        String betStr=moneyToBet.getText().toString();
        if(betStr.isEmpty()){
            return;
        }
        float bet= Float.valueOf(betStr.replace(",","."));
        if(win1.isChecked()){
            result= money+2*bet;
            moneyToSpend.setText(String.format(new Locale("PL"),"%.2f $",result));
            money=result;
        }
        else if(win2.isChecked()){
            result= money-bet;
            moneyToSpend.setText(String.format(new Locale("PL"),"%.2f $",result));
            money=result;
        }
        else if(draw.isChecked()){
            result= money-bet;
            moneyToSpend.setText(String.format(new Locale("PL"),"%.2f $",result));
            money=result;
        }
        else{
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Musisz obstawić jakiś mecz");
            alert.setTitle("UWAGA!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                }
            });

            alert.show();
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