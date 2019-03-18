package com.robgas.theguardian.Network;

import com.robgas.theguardian.Network.model.FeedResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/search?show-fields=thumbnail&page-size=15&format=json")
    Call<FeedResponse> getFeed(@Query("page") int pageNumber);

}
