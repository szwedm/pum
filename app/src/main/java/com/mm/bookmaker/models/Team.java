package com.mm.bookmaker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Team {

    @PrimaryKey(autoGenerate = true)
    private int id;

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

    @Ignore
    public Team(int id, String name, int matchesPlayed, int wins, int loses, int draws) {
        this.id = id;
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.wins = wins;
        this.loses = loses;
        this.draws = draws;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLoses() {
        return loses;
    }

    public void setLoses(int loses) {
        this.loses = loses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
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
