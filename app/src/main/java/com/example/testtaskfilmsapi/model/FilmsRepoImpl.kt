package com.example.testtaskfilmsapi.model

import com.example.testtaskfilmsapi.remote.ApiHolder
import com.example.testtaskfilmsapi.remote.FilmsRepo
import retrofit2.Callback

class FilmsRepoImpl : FilmsRepo {

    override fun getFilms(callback: Callback<Films>) = ApiHolder.getData(callback)
}
