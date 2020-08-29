package com.mm.bookmaker.extractors;

import com.mm.bookmaker.models.Match;
import com.mm.bookmaker.models.Player;
import com.mm.bookmaker.models.Team;
import com.mm.bookmaker.models.retrofit.MatchesResponse;
import com.mm.bookmaker.models.retrofit.StandingsResponse;
import com.mm.bookmaker.models.retrofit.TopScorersResponse;

import java.util.ArrayList;
import java.util.List;

public class ResponseToModel {

    public static void extractTeamsFromStandingsResponse(StandingsResponse response, List<Team> teams) {
        List<StandingsResponse.Api.Standing> standings = new ArrayList<>(response.getApi().getStandings().get(0));
        for(StandingsResponse.Api.Standing standing : standings) {
            teams.add(new Team(standing.getTeamId(),
                    standing.getTeamName(),
                    standing.getRank(),
                    standing.getAll().getMatchsPlayed(),
                    standing.getPoints(),
                    standing.getAll().getWin(),
                    standing.getAll().getLose(),
                    standing.getAll().getDraw()));
        }
    }

    public static void extractMatchesFromMatchesResponse(MatchesResponse response, List<Match> matches) {
        List<MatchesResponse.Api.Fixture> fixtures = new ArrayList<>(response.getApi().getFixtures());
        for (MatchesResponse.Api.Fixture fixture : fixtures) {
            matches.add(new Match(fixture.getFixtureId(),
                    fixture.getEventDate(),
                    fixture.getStatusShort(),
                    fixture.getHomeTeam().getTeamName(),
                    fixture.getAwayTeam().getTeamName(),
                    fixture.getGoalsHomeTeam(),
                    fixture.getGoalsAwayTeam()));
        }
    }

    public static void extractTopScorersFromTopScorersResponse(TopScorersResponse response, List<Player> players) {
        List<TopScorersResponse.Api.Topscorer> topScorers = new ArrayList<>(response.getApi().getTopscorers());
        for (TopScorersResponse.Api.Topscorer topScorer : topScorers) {
            players.add(new Player(topScorer.getPlayerId(),
                    topScorer.getPlayerName(),
                    topScorer.getFirstname(),
                    topScorer.getLastname(),
                    topScorer.getPosition(),
                    topScorer.getTeamId(),
                    topScorer.getTeamName(),
                    topScorer.getGoals().getTotal()));
        }
    }
}
