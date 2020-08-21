package com.mm.bookmaker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;

@Entity
public class Match {

    @PrimaryKey
    private Long id;

    @ColumnInfo(name = "date")
    private LocalDate date;

    @ColumnInfo(name = "team1")
    private Team team1;

    @ColumnInfo(name = "team2")
    private Team team2;

    @ColumnInfo(name = "status")
    private String status; // "coming" or "finished"

    @ColumnInfo(name = "team1_goals")
    private int team1Goals;

    @ColumnInfo(name = "team2_goals")
    private int team2Goals;

    public Match() {
    }

    public Match(Long id, LocalDate date, Team team1, Team team2) {
        this.id = id;
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.status = "coming";
        this.team1Goals = 0;
        this.team2Goals = 0;
    }

    public Match(Long id, LocalDate date, Team team1, Team team2, int team1Goals, int team2Goals) {
        this.id = id;
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.status = "finished";
        this.team1Goals = team1Goals;
        this.team2Goals = team2Goals;
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

    public int getTeam1Goals() {
        return team1Goals;
    }

    public int getTeam2Goals() { return team2Goals; }

    public String getScore() {
        return team1Goals + " - " + team2Goals;
    }
}
