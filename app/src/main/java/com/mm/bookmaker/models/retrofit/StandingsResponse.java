package com.mm.bookmaker.models.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StandingsResponse {

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

        @SerializedName("standings")
        @Expose
        private List<List<Standing>> standings = null;

        public Integer getResults() {
            return results;
        }

        public void setResults(Integer results) {
            this.results = results;
        }

        public List<List<Standing>> getStandings() {
            return standings;
        }

        public void setStandings(List<List<Standing>> standings) {
            this.standings = standings;
        }

        public static class Standing {

            @SerializedName("rank")
            @Expose
            private Integer rank;

            @SerializedName("team_id")
            @Expose
            private Integer teamId;

            @SerializedName("teamName")
            @Expose
            private String teamName;

            @SerializedName("all")
            @Expose
            private All all;

            @SerializedName("points")
            @Expose
            private Integer points;

            public Integer getRank() {
                return rank;
            }

            public void setRank(Integer rank) {
                this.rank = rank;
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

            public All getAll() {
                return all;
            }

            public void setAll(All all) {
                this.all = all;
            }

            public Integer getPoints() {
                return points;
            }

            public void setPoints(Integer points) {
                this.points = points;
            }

            public static class All {

                @SerializedName("matchsPlayed")
                @Expose
                private Integer matchsPlayed;

                @SerializedName("win")
                @Expose
                private Integer win;

                @SerializedName("draw")
                @Expose
                private Integer draw;

                @SerializedName("lose")
                @Expose
                private Integer lose;

                public Integer getMatchsPlayed() {
                    return matchsPlayed;
                }

                public void setMatchsPlayed(Integer matchsPlayed) {
                    this.matchsPlayed = matchsPlayed;
                }

                public Integer getWin() {
                    return win;
                }

                public void setWin(Integer win) {
                    this.win = win;
                }

                public Integer getDraw() {
                    return draw;
                }

                public void setDraw(Integer draw) {
                    this.draw = draw;
                }

                public Integer getLose() {
                    return lose;
                }

                public void setLose(Integer lose) {
                    this.lose = lose;
                }
            }
        }
    }
}
