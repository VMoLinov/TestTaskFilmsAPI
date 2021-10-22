package com.example.testtaskfilmsapi.model

import com.example.testtaskfilmsapi.remote.ApiHolder
import com.example.testtaskfilmsapi.remote.FilmsRepo

class FilmsRepoImpl : FilmsRepo {

    override fun getFilms(callback: retrofit2.Callback<Films>) = ApiHolder.getData(callback)
}
