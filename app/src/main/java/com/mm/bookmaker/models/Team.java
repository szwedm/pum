package com.mm.bookmaker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Team {

    @PrimaryKey
    private Long id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "matches")
    private int matchesPlayed;

    @ColumnInfo(name = "wins")
    private int wins;

    @ColumnInfo(name = "loses")
    private int loses;

    @ColumnInfo(name = "draws")
    private int draws;

    public Team() {
    }

    public Team(Long id, String name, int matchesPlayed, int wins, int loses, int draws) {
        this.id = id;
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() { return loses; }

    public int getDraws() {
        return draws;
    }

    public void win() {
        wins++;
    }

    public void loss() {
        loses++;
    }

    public void draw() { draws++; }

    @Override
    public String toString() {
        return name;
    }
}
