package com.github.chiefchiefon.goofigiffy.model.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApiService {
    @GET("gifs/trending")
    fun fetchTrendingGifs(@Query("api_key") apikey: String): Call<GiphyResponse>
}