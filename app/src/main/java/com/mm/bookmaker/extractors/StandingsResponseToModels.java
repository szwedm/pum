package com.mm.bookmaker.extractors;

import com.mm.bookmaker.models.Team;
import com.mm.bookmaker.models.retrofit.StandingsResponse;

import java.util.ArrayList;
import java.util.List;

public class StandingsResponseToModels {

    public static void extractFromResponse(StandingsResponse response, List<Team> teams) {
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
}
