package com.mm.bookmaker.models;

public class Player {

    private Long id;
    private String firstName;
    private String lastName;
    private String position;
    private Team team;
    private int goals;

    public Player() {
    }

    public Player(Long id, String firstName, String lastName, String position, Team team, int goals) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public int getGoals() {
        return goals;
    }
}
