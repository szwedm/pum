package com.mm.bookmaker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mm.bookmaker.R;
import com.mm.bookmaker.models.Bet;
import com.mm.bookmaker.models.Match;

import java.util.List;

public class BetArrayAdapter extends ArrayAdapter<Bet> {

    private final Context context;
    private final List<Bet> bets;
    private final List<Match> matches;

    public BetArrayAdapter(@NonNull Context context, @NonNull List<Bet> betsList, @NonNull List<Match> matchesList) {
        super(context, -1, betsList);
        this.context = context;
        this.bets = betsList;
        this.matches = matchesList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View betListView = inflater.inflate(R.layout.list_bet_model, parent, false);
        TextView nrTextView = (TextView) betListView.findViewById(R.id.betListTextView1);
        TextView dateAndTimeTextView = (TextView) betListView.findViewById(R.id.betListTextView2);
        TextView homeTeamTextView = (TextView) betListView.findViewById(R.id.betListTextView3);
        TextView awayTeamTextView = (TextView) betListView.findViewById(R.id.betListTextView4);
        TextView valueTextView = (TextView) betListView.findViewById(R.id.betListTextView5);
        TextView typeTextView = (TextView) betListView.findViewById(R.id.betListTextView6);
        TextView statusTextView = (TextView) betListView.findViewById(R.id.betListTextView7);

        nrTextView.setText(String.valueOf(bets.get(position).getId()));
        dateAndTimeTextView.setText(formatDateAndTime(matches.get(position).getDate()));
        homeTeamTextView.setText(matches.get(position).getHomeTeamName());
        awayTeamTextView.setText(matches.get(position).getAwayTeamName());
        valueTextView.setText(String.valueOf(bets.get(position).getValue()));
        typeTextView.setText(bets.get(position).getType());
        statusTextView.setText(bets.get(position).getStatus());

        return  betListView;
    }

    private String formatDateAndTime(String matchDate) {
        String[] matchDateSplit = matchDate.split("T");
        String[] dateSplit = matchDateSplit[0].split("-");
        String resultString =  dateSplit[2] + "." + dateSplit[1] +
                " " + matchDateSplit[1].substring(0, 5);
        return  resultString;
    }
}
