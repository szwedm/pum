package com.mm.bookmaker.models.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamsResponse {

    @SerializedName("api")
    @Expose
    private Api api;

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public static class Api {

        @SerializedName("results")
        @Expose
        private Integer results;

        @SerializedName("teams")
        @Expose
        private List<Team> teams = null;

        public Integer getResults() {
            return results;
        }

        public void setResults(Integer results) {
            this.results = results;
        }

        public List<Team> getTeams() {
            return teams;
        }

        public void setTeams(List<Team> teams) {
            this.teams = teams;
        }

        public static class Team {

            @SerializedName("team_id")
            @Expose
            private Integer teamId;

            @SerializedName("name")
            @Expose
            private String name;

            public Integer getTeamId() {
                return teamId;
            }

            public void setTeamId(Integer teamId) {
                this.teamId = teamId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
