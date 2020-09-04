package com.mm.bookmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mm.bookmaker.database.AppDatabase;
import com.mm.bookmaker.models.Bet;
import com.mm.bookmaker.models.Match;

public class BetActivity extends AppCompatActivity {

    private int value;
    private int money;
    private String type;
    private AppDatabase db;
    private Bet bet;
    private Match match;

    private TextView textViewDate;
    private TextView textViewHomeTeamName;
    private TextView textViewAwayTeamName;
    private EditText editTextValue;
    private RadioButton radioButton1;
    private RadioButton radioButtonX;
    private RadioButton radioButton2;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet);

        db = AppDatabase.getInstance(getApplicationContext());

        Intent intent = getIntent();
        match = (Match) intent.getSerializableExtra("match");
        money = intent.getIntExtra("money", 0);

        bet = db.betDao().findByMatchId(match.getId());

        textViewDate = (TextView) findViewById(R.id.bet_textView1);
        textViewHomeTeamName = (TextView) findViewById(R.id.bet_textView3);
        textViewAwayTeamName = (TextView) findViewById(R.id.bet_textView5);
        editTextValue = (EditText) findViewById(R.id.bet_editTextNumber);
        radioButton1 = (RadioButton) findViewById(R.id.bet_radioButton1);
        radioButtonX = (RadioButton) findViewById(R.id.bet_radioButton2);
        radioButton2 = (RadioButton) findViewById(R.id.bet_radioButton3);
        button = (Button) findViewById(R.id.bet_button);

        textViewDate.setText(match.getDate());
        textViewHomeTeamName.setText(match.getHomeTeamName());
        textViewAwayTeamName.setText(match.getAwayTeamName());

        if (bet != null) {
            switch(bet.getType()) {
                case "1":
                    radioButton1.setChecked(true);
                    break;
                case "X":
                    radioButtonX.setChecked(true);
                    break;
                case "2":
                    radioButton2.setChecked(true);
                    break;
            }

            radioButton1.setEnabled(false);
            radioButtonX.setEnabled(false);
            radioButton2.setEnabled(false);
            editTextValue.setText(bet.getValue().toString());
            editTextValue.setEnabled(false);
            button.setVisibility(View.GONE);
        }
    }

    public void onRadioButtonClick(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.bet_radioButton1:
                if (checked) type = "1";
                break;
            case R.id.bet_radioButton2:
                if (checked) type = "X";
                break;
            case R.id.bet_radioButton3:
                if (checked) type = "2";
                break;
        }
    }

    public void createBet(View view) {
        value = Integer.parseInt(editTextValue.getText().toString());
        if (value <= money && value > 0 && !(type.isEmpty())) {
            bet = new Bet(match.getId(), value, type, "P");
            db.betDao().insertBet(bet);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("betValue", value);
            setResult(RESULT_OK, resultIntent);
            finish();
        } else if (value == 0 || value > money || type.isEmpty()) {
            Toast.makeText(BetActivity.this, "Wartosc zakladu musi byc wieksza od 0", Toast.LENGTH_LONG).show();
        }
    }
}