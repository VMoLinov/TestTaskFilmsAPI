package com.example.testtaskfilmsapi.remote

import com.example.testtaskfilmsapi.model.Films
import retrofit2.Callback

interface FilmsRepo {

    fun getFilms(callback: Callback<Films>)
}
