package com.example.testtaskfilmsapi.view.main

import android.util.Log
import com.example.testtaskfilmsapi.model.Film
import com.example.testtaskfilmsapi.model.Films
import com.example.testtaskfilmsapi.remote.FilmsRepo
import moxy.MvpPresenter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.terrakok.cicerone.Router

class MainFragmentPresenter(
    private val data: FilmsRepo,
    private val router: Router
) : MvpPresenter<MainFragmentView>() {

    private var d: List<Film>? = null
    private val callback = object : Callback<Films> {
        override fun onResponse(
            call: Call<Films>,
            response: Response<Films>
        ) {
            val serverResponse: Films? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                d = serverResponse.films
                Log.d(LOG, "success " + d.toString())
            } else {
                Log.d(LOG, "empty data " + response.code())
            }
        }

        override fun onFailure(call: Call<Films>, t: Throwable) {
            Log.d(LOG, "error " + t.printStackTrace())
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
    }

    private fun loadData() {
        data.getFilms(callback)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    companion object {
        val LOG = "RETROFIT"
    }
}
