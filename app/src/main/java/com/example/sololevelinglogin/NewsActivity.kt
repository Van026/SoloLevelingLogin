package com.example.sololevelinglogin

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sololevelinglogin.api.NewsResponse
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(NewsApiService::class.java)
        val call = service.getTopHeadlines("us", "c63be83bba914406b602cc6e04511269")

        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    newsResponse?.articles?.forEach {
                        Log.d("News", "Title: ${it.title}")
                    }
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Toast.makeText(this@NewsActivity, "Failed to load news", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
