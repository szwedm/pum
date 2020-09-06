package com.mm.bookmaker;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.mm.bookmaker.adapters.BetArrayAdapter;
import com.mm.bookmaker.database.AppDatabase;
import com.mm.bookmaker.models.Bet;
import com.mm.bookmaker.models.Match;

import java.util.ArrayList;
import java.util.List;

public class BetsActivity extends AppCompatActivity {

    private AppDatabase db;
    private List<Bet> bets;
    private List<Match> matches;
    private ListView betsListView;
    private BetArrayAdapter betAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bets);

        db = AppDatabase.getInstance(getApplicationContext());
        betsListView = (ListView) findViewById(R.id.betsListView);
        bets = new ArrayList<>(db.betDao().getAllSortById());
        matches = new ArrayList<>();
        for (Bet bet : bets) {
            matches.add(db.matchDao().findById(bet.getMatch_id()));
        }

        betAdapter = new BetArrayAdapter(getApplicationContext(), bets, matches);
        betsListView.setAdapter(betAdapter);
    }
}