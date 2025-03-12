package com.example.sololevelinglogin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sololevelinglogin.api.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        fetchNews()
    }

    private fun fetchNews() {
        val apiKey = "c63be83bba914406b602cc6e04511269" // Replace with your actual API key
        val call = RetrofitInstance.api.getTopHeadlines("us", apiKey)

        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                if (response.isSuccessful) {
                    val newsResponse = response.body()
                    newsResponse?.articles?.forEach {
                        Log.d("NEWS", "Title: ${it.title}")
                    }
                } else {
                    Log.e("ERROR", "Failed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ERROR", "Network Error: ${t.message}")
            }
        })
    }
}
