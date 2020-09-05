package com.mm.bookmaker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "match")
public class Match implements Serializable {

    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "event_timestamp")
    private Long eventTimeStamp;

    @ColumnInfo(name = "statusShort")
    private String statusShort;

    @ColumnInfo(name = "homeTeam")
    private String homeTeamName;

    @ColumnInfo(name = "awayTeam")
    private String awayTeamName;

    @ColumnInfo(name = "homeTeam_goals")
    private int homeTeamGoals;

    @ColumnInfo(name = "awayTeam_goals")
    private int awayTeamGoals;

    public Match(Integer id, String date, Long eventTimeStamp, String statusShort, String homeTeamName, String awayTeamName, int homeTeamGoals, int awayTeamGoals) {
        this.id = id;
        this.date = date;
        this.eventTimeStamp = eventTimeStamp;
        this.statusShort = statusShort;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamGoals = homeTeamGoals;
        this.awayTeamGoals = awayTeamGoals;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getEventTimeStamp() { return eventTimeStamp; }

    public void setEventTimeStamp(Long eventTimeStamp) { this.eventTimeStamp = eventTimeStamp; }

    public String getStatusShort() {
        return statusShort;
    }

    public void setStatusShort(String statusShort) {
        this.statusShort = statusShort;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public int getHomeTeamGoals() {
        return homeTeamGoals;
    }

    public void setHomeTeamGoals(int homeTeamGoals) {
        this.homeTeamGoals = homeTeamGoals;
    }

    public int getAwayTeamGoals() {
        return awayTeamGoals;
    }

    public void setAwayTeamGoals(int awayTeamGoals) {
        this.awayTeamGoals = awayTeamGoals;
    }
}
