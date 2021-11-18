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
                itemsListPresenter.films.addAll(serverResponse.films)
                val genres: MutableSet<String> = mutableSetOf()
                serverResponse.films.forEach {
                    it.genres?.forEach { genre ->
                        genres.add(genre)
                    }
                }
                itemsListPresenter.genres.addAll(genres)
                itemsListPresenter.apply {
                    data = (genres + films).toList()
                }
                viewState.renderData()
            } else {
                Log.d(TAG_MAIN_FRAGMENT, "empty data " + response.code())
            }
        }

        override fun onFailure(call: Call<Films>, t: Throwable) {
            Log.d(TAG_MAIN_FRAGMENT, "error " + t.printStackTrace())
        }
    }

    class FilmsListPresenter {

        var films = mutableListOf<Film>()
        var genres = mutableListOf<String>()
        var data: List<Any> = mutableListOf()

        var itemCLickListener: ((MainItemView) -> Unit)? = null

        fun getCount(): Int = data.size

        fun bindFilm(holder: MainAdapter.FilmsViewHolder) {
            val item = data[holder.pos] as Film
            holder.loadString(item.localized_name)
            holder.loadImage(item.image_url)
        }

        fun bindGenre(holder: MainAdapter.GenreViewHolder) {
            holder.loadString(data[holder.pos] as String)
        }
    }

    val itemsListPresenter = FilmsListPresenter()

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
        const val TAG_MAIN_FRAGMENT = "RETROFIT"
    }
}
