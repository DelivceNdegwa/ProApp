package com.example.proapp.networking.services;

import com.example.proapp.networking.pojos.ProfessionResponse;
import com.example.proapp.networking.pojos.ProfessionalResponse;
import com.example.proapp.networking.pojos.TestResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface EndPoints {
    @GET("comments")
    Call<List<TestResponse>> getComments();

    @GET("/professions")
    Call<List<ProfessionResponse>> getProfessions();

    @GET("/professions/{id}")
    Call<ProfessionResponse> getProfessionData(@Path("id") int id);

    @GET("/professionals")
    Call<List<ProfessionalResponse>> getProfessionals();

    @GET("/professionals/{id}")
    Call<ProfessionalResponse> getProfessionalData(@Path("id") int id);
}
