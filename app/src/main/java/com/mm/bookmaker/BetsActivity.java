package com.mm.bookmaker;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mm.bookmaker.database.AppDatabase;
import com.mm.bookmaker.models.Bet;

import java.util.ArrayList;
import java.util.List;

public class BetsActivity extends AppCompatActivity {

    private AppDatabase db;
    private List<Bet> bets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bets);

        db = AppDatabase.getInstance(getApplicationContext());
        bets = new ArrayList<>(db.betDao().getAll());
    }
}