package com.example.betterwine.dosth.Interface;


import com.example.betterwine.dosth.Model.Chat;
import com.example.betterwine.dosth.Model.TodayThing;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitInterface {

    @GET("HistoryToday/LookUp")
    Observable<TodayThing> getTodayThing(
            @Query("key") String key,
            @Query("yue") int yue,
            @Query("ri")int ri,
            @Query("type") int type);

    @GET("openapi/api")
    Observable<Chat> getChat(
            @Query("key") String key,
            @Query("info") String info
            );
}
