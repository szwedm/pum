package com.mm.bookmaker.models.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopScorersResponse {

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
        @SerializedName("topscorers")
        @Expose
        private List<Topscorer> topscorers = null;

        public Integer getResults() {
            return results;
        }

        public void setResults(Integer results) {
            this.results = results;
        }

        public List<Topscorer> getTopscorers() {
            return topscorers;
        }

        public void setTopscorers(List<Topscorer> topscorers) {
            this.topscorers = topscorers;
        }

        public class Topscorer {

            @SerializedName("player_id")
            @Expose
            private Integer playerId;

            @SerializedName("player_name")
            @Expose
            private String playerName;

            @SerializedName("firstname")
            @Expose
            private String firstname;

            @SerializedName("lastname")
            @Expose
            private String lastname;

            @SerializedName("position")
            @Expose
            private String position;

            @SerializedName("team_id")
            @Expose
            private Integer teamId;

            @SerializedName("team_name")
            @Expose
            private String teamName;

            @SerializedName("goals")
            @Expose
            private Goals goals;

            public Integer getPlayerId() {
                return playerId;
            }

            public void setPlayerId(Integer playerId) {
                this.playerId = playerId;
            }

            public String getPlayerName() {
                return playerName;
            }

            public void setPlayerName(String playerName) {
                this.playerName = playerName;
            }

            public String getFirstname() {
                return firstname;
            }

            public void setFirstname(String firstname) {
                this.firstname = firstname;
            }

            public String getLastname() {
                return lastname;
            }

            public void setLastname(String lastname) {
                this.lastname = lastname;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public Integer getTeamId() {
                return teamId;
            }

            public void setTeamId(Integer teamId) {
                this.teamId = teamId;
            }

            public String getTeamName() {
                return teamName;
            }

            public void setTeamName(String teamName) {
                this.teamName = teamName;
            }

            public Goals getGoals() {
                return goals;
            }

            public void setGoals(Goals goals) {
                this.goals = goals;
            }

            public class Goals {

                @SerializedName("total")
                @Expose
                private Integer total;

                public Integer getTotal() {
                    return total;
                }

                public void setTotal(Integer total) {
                    this.total = total;
                }
            }
        }
    }
}
