package com.mm.bookmaker.services;

import com.mm.bookmaker.models.retrofit.MatchesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface MatchService {

    @Headers("X-RapidAPI-Key: 11d75311b46aa1a2b1cd14c58012b8de")
    @GET("fixtures/league/2680?timezone=Europe/Warsaw")
    public Call<MatchesResponse> getMatches();
}
