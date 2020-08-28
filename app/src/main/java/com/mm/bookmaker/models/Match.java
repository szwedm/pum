package com.mm.bookmaker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Match {

    @PrimaryKey
    private Long id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "team1")
    private String team1;

    @ColumnInfo(name = "team2")
    private String team2;

    @ColumnInfo(name = "status")
    private String status; // "coming" or "finished"

    @ColumnInfo(name = "team1_goals")
    private int team1Goals;

    @ColumnInfo(name = "team2_goals")
    private int team2Goals;

    public Match() {
    }

    @Ignore
    public Match(Long id, String date, String team1, String team2) {
        this.id = id;
        this.date = date;
        this.team1 = team1;
        this.team2 = team2;
        this.status = "coming";
        this.team1Goals = 0;
        this.team2Goals = 0;
    }

    @Ignore
    public Match(Long id, String date, String team1, String team2, int team1Goals, int team2Goals) {
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

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
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

    public void setTeam1Goals(int team1Goals) {
        this.team1Goals = team1Goals;
    }

    public int getTeam2Goals() {
        return team2Goals;
    }

    public void setTeam2Goals(int team2Goals) {
        this.team2Goals = team2Goals;
    }

    public String getScore() {
        return team1Goals + " - " + team2Goals;
    }
}
