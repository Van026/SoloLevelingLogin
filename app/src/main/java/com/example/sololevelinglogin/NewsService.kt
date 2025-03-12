package com.example.sololevelinglogin.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("top-headlines")
    fun getTopHeadlines(
        @Query("country") country: String,
        @Query("c63be83bba914406b602cc6e04511269") apiKey: String
    ): Call<NewsResponse>
}
