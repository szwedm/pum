package com.mm.bookmaker.models;

import java.time.LocalDate;

public class Match {

    private Long id;
    private LocalDate date;
    private Team[] teams = new Team[2];
    private String status; // "coming" or "finished"
    private int team0Goals;
    private int team1Goals;

    public Match() {
    }

    public Match(Long id, LocalDate date, Team team0, Team team1) {
        this.id = id;
        this.date = date;
        this.teams[0] = team0;
        this.teams[1] = team1;
        this.status = "coming";
        this.team0Goals = 0;
        this.team1Goals = 0;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTeam0Goals() {
        return team0Goals;
    }

    public int getTeam1Goals() {
        return team1Goals;
    }

    public String getScore() {
        return team0Goals + " - " + team1Goals;
    }
}
