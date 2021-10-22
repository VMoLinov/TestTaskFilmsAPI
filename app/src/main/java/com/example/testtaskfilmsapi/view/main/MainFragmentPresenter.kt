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

    private val callback = object : Callback<Films> {
        override fun onResponse(
            call: Call<Films>,
            response: Response<Films>
        ) {
            val serverResponse: Films? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                itemsListPresenter.items.addAll(serverResponse.films)
                viewState.renderData()
            } else {
                Log.d(LOG, "empty data " + response.code())
            }
        }

        override fun onFailure(call: Call<Films>, t: Throwable) {
            Log.d(LOG, "error " + t.printStackTrace())
        }
    }

    class ItemsListPresenter : IMainListPresenter {

        var items = mutableListOf<Film>()

        override var itemCLickListener: ((MainItemView) -> Unit)? = null

        override fun getCount(): Int = items.size

        override fun bindView(view: MainItemView) {
            val item = items[view.pos]
            view.showName(item.name)
            view.loadImage(item.image_url)
        }
    }

    val itemsListPresenter = ItemsListPresenter()

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
        const val LOG = "RETROFIT"
    }
}
