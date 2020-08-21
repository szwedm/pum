package com.mm.bookmaker.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Player {

    @PrimaryKey
    private Long id;

    @ColumnInfo(name = "first_name")
    private String firstName;

    @ColumnInfo(name = "last_name")
    private String lastName;

    @ColumnInfo(name = "position")
    private String position;

    @ColumnInfo(name = "team_name")
    private String teamName;

    @ColumnInfo(name = "goals")
    private int goals;

    public Player() {
    }

    public Player(Long id, String firstName, String lastName, String position, String teamName, int goals) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.teamName = teamName;
        this.goals = goals;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public String getTeam() {
        return teamName;
    }

    public int getGoals() {
        return goals;
    }
}
