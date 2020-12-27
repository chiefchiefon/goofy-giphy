package com.github.chiefchiefon.goofigiffy.model.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

object GiphyApiServiceImpl {

    val service: GiphyApiService by lazy { getRetrofit().create(GiphyApiService::class.java)}

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.giphy.com/v1/")
            .addConverterFactory(
                Json { ignoreUnknownKeys = true }
                    .asConverterFactory(MediaType.get("application/json"))
            ).build()
    }

}