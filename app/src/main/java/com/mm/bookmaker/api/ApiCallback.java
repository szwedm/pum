package com.mm.bookmaker.api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCallback<T> implements Callback<T> {

    private T t;

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        t = response.body();
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
    }

    public T getResponse() {
        return t;
    }
}
