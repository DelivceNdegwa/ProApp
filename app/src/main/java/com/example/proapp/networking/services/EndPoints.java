package com.example.proapp.networking.services;

import com.example.proapp.networking.pojos.ProfessionResponse;
import com.example.proapp.networking.pojos.TestResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPoints {
    @GET("comments")
    Call<List<TestResponse>> getComments();

    @GET("/professions")
    Call<List<ProfessionResponse>> getProfessions();
}
