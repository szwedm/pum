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
import com.mm.bookmaker.models.Match;

import java.util.List;

public class MatchArrayAdapter extends ArrayAdapter<Match> {

    private final Context context;
    private final List<Match> matches;

    public MatchArrayAdapter(@NonNull Context context, @NonNull List<Match> list) {
        super(context, -1, list);
        this.context = context;
        this.matches = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View matchListView = inflater.inflate(R.layout.list_match_model, parent, false);
        TextView dateView = (TextView) matchListView.findViewById(R.id.listTextView1);
        TextView homeTeamView = (TextView) matchListView.findViewById(R.id.listTextView2);
        TextView awayTeamView = (TextView) matchListView.findViewById(R.id.listTextView3);

        dateView.setText(matches.get(position).getDate().substring(5, 10));
        homeTeamView.setText(matches.get(position).getHomeTeamName());
        awayTeamView.setText(matches.get(position).getAwayTeamName());

        return matchListView;
    }
}
