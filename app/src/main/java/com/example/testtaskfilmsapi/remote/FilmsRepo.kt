package com.example.testtaskfilmsapi.remote

import com.example.testtaskfilmsapi.model.Films

interface FilmsRepo {

    fun getFilms(callback: retrofit2.Callback<Films>)
}
