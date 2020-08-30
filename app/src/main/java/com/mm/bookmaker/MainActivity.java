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
import android.widget.RadioButton;
import android.widget.TextView;

import com.mm.bookmaker.database.AppDatabase;
import com.mm.bookmaker.models.Match;
import com.mm.bookmaker.models.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    List<Team> team= new ArrayList<>();
    //List<Match> match = new ArrayList<>();
    Match match;
    TextView moneyToSpend;
    TextView teamsThatPlay;
    RadioButton win1;
    EditText moneyToBet;
    RadioButton draw;
    RadioButton win2;
    Button betB;
    float money = 500;
    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teamsThatPlay= findViewById(R.id.teamsThatPlayTV);
        moneyToSpend= findViewById(R.id.moneyToSpendTV);
        moneyToBet= findViewById(R.id.moneyToBetET);
        betB= findViewById(R.id.betB);
        win1= (RadioButton)findViewById(R.id.winFirstTeamCB);
        draw= (RadioButton)findViewById(R.id.drawCB);
        win2= (RadioButton)findViewById(R.id.winSecondTeamCB);

        team.add(new Team((long)1,"Legia Warszawa",37, 21,6,10));
        team.add(new Team((long)2,"Lech Poznan",37, 18,7,12));
        team.add(new Team((long)3,"Piast Gliwice",37, 18,12,7));
        team.add(new Team((long)4,"Lechia Gdansk",37, 15,11,12));
        team.add(new Team((long)5,"Slask Wroclaw",37, 14,7,12));
        team.add(new Team((long)6,"Pogon Szczecin",37, 14,7,12));
        team.add(new Team((long)7,"Cracovia",37, 16,7,12));
        team.add(new Team((long)8,"Jagiellonia Bialystok",37, 14,7,12));
        team.add(new Team((long)9,"Gornik Zabrze",37, 14,7,12));
        team.add(new Team((long)10,"Rakow Czestochowa",37, 16,7,12));
        team.add(new Team((long)11,"KGHM Zaglebie Lubin",37, 15,7,12));
        team.add(new Team((long)12,"Wisla Plock",37, 14,7,12));
        team.add(new Team((long)13,"Wisla Krakow",37, 13,7,12));
        team.add(new Team((long)14,"Arka Gdynia",37, 10,10,17));
        team.add(new Team((long)15,"Korona Kielce",37, 9,8,20));
        team.add(new Team((long)16,"LKS Lodz",37, 6,6,25));

        match = new Match((long)1,"2020-08-30","Piast GLiwice","Legia Warszawa",2,3);
        teamsThatPlay.setText(String.format(new Locale("PL"),"%s vs %s",match.getTeam1(), match.getTeam2()));
        moneyToSpend.setText(String.format(new Locale("PL"),"%.2f $",money));

        db = AppDatabase.getInstance(getApplicationContext());
    }



    public void setMoneyToBet(View view){

        float result;
        String betStr=moneyToBet.getText().toString();
        if(betStr.isEmpty()){
            return;
        }
        float bet= Float.valueOf(betStr.replace(",","."));
        if(money<=0){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Nie masz wystarczająco pieniędzy");
            alert.setTitle("UWAGA!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });
            alert.show();
        }
        else if(bet>money){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setMessage("Musisz obstawić za mniej pieniędzy");
            alert.setTitle("UWAGA!");
            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                }
            });
            alert.show();
        }
        else{
            if(win1.isChecked()){
                if(match.getTeam1Goals()>match.getTeam2Goals()){
                    result = money + 2 * bet;
                    moneyToSpend.setText(String.format(new Locale("PL"), "%.2f $", result));
                    money = result;
                }
                else{
                    result= money-bet;
                    moneyToSpend.setText(String.format(new Locale("PL"),"%.2f $",result));
                    money=result;
                }
            }

            if(win2.isChecked() ){
                if(match.getTeam1Goals()<match.getTeam2Goals()){
                    result = money + 2 * bet;
                    moneyToSpend.setText(String.format(new Locale("PL"), "%.2f $", result));
                    money = result;
                }
                else{
                    result= money-bet;
                    moneyToSpend.setText(String.format(new Locale("PL"),"%.2f $",result));
                    money=result;
                }
            }

            if(draw.isChecked()){
                if(match.getTeam1Goals()==match.getTeam2Goals()){
                    result = money + 2 * bet;
                    moneyToSpend.setText(String.format(new Locale("PL"), "%.2f $", result));
                    money = result;
                }
                else{
                    result= money-bet;
                    moneyToSpend.setText(String.format(new Locale("PL"),"%.2f $",result));
                    money=result;
                }
            }

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