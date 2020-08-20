package com.mm.bookmaker.models;

import java.util.HashSet;
import java.util.Set;

public class Team {

    private Long id;
    private String name;
    private Set<Match> matchesPlayed = new HashSet<Match>();
    private int wins;
    private int loses;
    private int draws;

    public Team() {
    }

    public Team(Long id, String name, int wins, int loses, int draws) {
        this.id = id;
        this.name = name;
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

    public Set<Match> getMatchesPlayed() {
        return matchesPlayed;
    }

    public int getWins() {
        return wins;
    }

    public int getLoses() {
        return loses;
    }

    public int getDraws() {
        return draws;
    }

    public void win() {
        wins++;
    }

    public void loss() {
        loses++;
    }

    public void draw() {
        draws++;
    }
}
