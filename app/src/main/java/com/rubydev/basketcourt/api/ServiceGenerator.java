package com.rubydev.basketcourt.api;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by irfanandarafifsatrio on 11/1/17.
 */

public class ServiceGenerator {
    public static final String BASE_URL = "http://10.20.33.101:8000/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Service client = retrofit.create(Service.class);
}
