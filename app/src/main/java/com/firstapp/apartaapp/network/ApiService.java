package com.firstapp.apartaapp.network;

import com.firstapp.apartaapp.models.Announcement;
import com.firstapp.apartaapp.models.LoginRequest;
import com.firstapp.apartaapp.models.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    // Supabase Auth Login
    @POST("auth/v1/token")
    Call<LoginResponse> login(
            @Query("grant_type") String grantType,
            @Body LoginRequest loginRequest
    );

    // Get announcements from Supabase
    @GET("rest/v1/announcements")
    Call<List<Announcement>> getAnnouncements();
}