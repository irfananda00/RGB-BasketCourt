package com.rubydev.basketcourt.api;

import com.rubydev.basketcourt.model.ResMessage;
import com.rubydev.basketcourt.model.Score;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by irfanandarafifsatrio on 11/1/17.
 */

public interface Service {
    @GET("api/history")
    Call<ResMessage> listScore(
        @Query("nim") String nim
    );

    @FormUrlEncoded
    @POST("api/history")
    Call<ResMessage> addScore(
        @Field("team_a") String team_a,
        @Field("team_b") String team_b,
        @Field("score_a") String score_a,
        @Field("score_b") String score_b,
        @Field("date") String date,
        @Field("nim") String nim
    );
}
