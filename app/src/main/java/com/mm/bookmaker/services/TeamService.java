package com.mm.bookmaker.services;

import com.mm.bookmaker.models.retrofit.StandingsResponse;
import com.mm.bookmaker.models.retrofit.TeamsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface TeamService {

    @Headers("X-RapidAPI-Key: 11d75311b46aa1a2b1cd14c58012b8de")
    @GET("/leagueTable/2680")
    public Call<StandingsResponse> getTeamsAndStandings();
}
