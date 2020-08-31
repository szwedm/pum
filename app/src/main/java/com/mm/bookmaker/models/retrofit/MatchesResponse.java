package com.mm.bookmaker.models.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MatchesResponse {

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

        @SerializedName("fixtures")
        @Expose
        private List<Fixture> fixtures = null;

        public Integer getResults() {
            return results;
        }

        public void setResults(Integer results) {
            this.results = results;
        }

        public List<Fixture> getFixtures() {
            return fixtures;
        }

        public void setFixtures(List<Fixture> fixtures) {
            this.fixtures = fixtures;
        }

        public static class Fixture {

            @SerializedName("fixture_id")
            @Expose
            private Integer fixtureId;

            @SerializedName("event_date")
            @Expose
            private String eventDate;

            @SerializedName("statusShort")
            @Expose
            private String statusShort;

            @SerializedName("homeTeam")
            @Expose
            private HomeTeam homeTeam;

            @SerializedName("awayTeam")
            @Expose
            private AwayTeam awayTeam;

            @SerializedName("goalsHomeTeam")
            @Expose
            private Integer goalsHomeTeam;

            @SerializedName("goalsAwayTeam")
            @Expose
            private Integer goalsAwayTeam;

            public Integer getFixtureId() {
                return fixtureId;
            }

            public void setFixtureId(Integer fixtureId) {
                this.fixtureId = fixtureId;
            }

            public String getEventDate() {
                return eventDate;
            }

            public void setEventDate(String eventDate) {
                this.eventDate = eventDate;
            }

            public String getStatusShort() {
                return statusShort;
            }

            public void setStatusShort(String statusShort) {
                this.statusShort = statusShort;
            }

            public HomeTeam getHomeTeam() {
                return homeTeam;
            }

            public void setHomeTeam(HomeTeam homeTeam) {
                this.homeTeam = homeTeam;
            }

            public AwayTeam getAwayTeam() {
                return awayTeam;
            }

            public void setAwayTeam(AwayTeam awayTeam) {
                this.awayTeam = awayTeam;
            }

            public Integer getGoalsHomeTeam() {
                return goalsHomeTeam;
            }

            public void setGoalsHomeTeam(Integer goalsHomeTeam) {
                this.goalsHomeTeam = goalsHomeTeam;
            }

            public Integer getGoalsAwayTeam() {
                return goalsAwayTeam;
            }

            public void setGoalsAwayTeam(Integer goalsAwayTeam) {
                this.goalsAwayTeam = goalsAwayTeam;
            }

            public static class AwayTeam {

                @SerializedName("team_name")
                @Expose
                private String teamName;

                public String getTeamName() {
                    return teamName;
                }

                public void setTeamName(String teamName) {
                    this.teamName = teamName;
                }
            }

            public static class HomeTeam {

                @SerializedName("team_name")
                @Expose
                private String teamName;

                public String getTeamName() {
                    return teamName;
                }

                public void setTeamName(String teamName) {
                    this.teamName = teamName;
                }
            }
        }
    }
}
