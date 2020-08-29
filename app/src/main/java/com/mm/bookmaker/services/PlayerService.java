package com.mm.bookmaker.services;

import com.mm.bookmaker.models.retrofit.TopScorersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PlayerService {

    @Headers("X-RapidAPI-Key: 11d75311b46aa1a2b1cd14c58012b8de")
    @GET("topscorers/2680")
    public Call<TopScorersResponse> getTopScorers();
}
