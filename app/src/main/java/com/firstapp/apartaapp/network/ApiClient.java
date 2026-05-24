package com.firstapp.apartaapp.network;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://vwchutepyfniuydulcrq.supabase.co/";
    private static final String SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InZ3Y2h1dGVweWZuaXV5ZHVsY3JxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Nzk1NjkwNzksImV4cCI6MjA5NTE0NTA3OX0.iaFqTHHsaC9v0YnTyfT34NA2SfAMZaBzGIUaqSvaXG4"; // Replace with your actual anon key

    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request original = chain.request();
                        Request request = original.newBuilder()
                                .header("apikey", SUPABASE_KEY)
                                .header("Authorization", "Bearer " + SUPABASE_KEY)
                                .method(original.method(), original.body())
                                .build();
                        return chain.proceed(request);
                    })
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}