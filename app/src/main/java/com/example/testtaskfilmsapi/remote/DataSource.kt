package com.example.testtaskfilmsapi.remote

import com.example.testtaskfilmsapi.model.Films
import retrofit2.Call
import retrofit2.http.GET

interface DataSource {

    @GET("films.json")
    fun loadData(): Call<Films>
}
