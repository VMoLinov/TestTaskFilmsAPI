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
import java.util.*

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
                filmsListPresenter.films.addAll(serverResponse.films)
                val genres: MutableSet<String> = mutableSetOf()
                serverResponse.films.forEach {
                    it.genres?.forEach { genre ->
                        genres.add(genre)
                    }
                }
                filmsListPresenter.genres.addAll(genres)
                filmsListPresenter.apply {
                    data = (TreeSet(genres) + films).toList()
                    cachedData = data.toList()
                }
                viewState.renderData()
            } else {
                Log.d(TAG, "empty data " + response.code())
            }
        }

        override fun onFailure(call: Call<Films>, t: Throwable) {
            Log.d(TAG, "error " + t.printStackTrace())
        }
    }

    class FilmsListPresenter : ListPresenter<MainAdapter.BaseViewHolder> {

        var films = mutableListOf<Film>()
        var genres = mutableListOf<String>()
        var data: List<Any> = mutableListOf()
        var cachedData: List<Any> = mutableListOf()
        override var itemCLickListener: ((MainAdapter.BaseViewHolder) -> Unit)? = null

        override fun getCount(): Int = data.size

        override fun bind(view: MainAdapter.BaseViewHolder) {
            when (view) {
                is MainAdapter.GenreViewHolder -> bindGenre(view)
                is MainAdapter.FilmsViewHolder -> bindFilm(view)
            }
        }

        private fun bindGenre(holder: MainAdapter.GenreViewHolder) {
            holder.loadString(data[holder.pos] as String)
        }

        private fun bindFilm(holder: MainAdapter.FilmsViewHolder) {
            val item = data[holder.pos] as Film
            holder.loadString(item.localized_name)
            holder.loadImage(item.image_url)
        }

    }

    val filmsListPresenter = FilmsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()
        setListener()
    }

    private fun setListener() {
        filmsListPresenter.apply {
            itemCLickListener = {
                when (it) {
                    is MainAdapter.GenreViewHolder -> {
                    }
                    is MainAdapter.FilmsViewHolder -> {
                    }
                }
            }
        }
    }

    private fun loadData() {
        data.getFilms(callback)
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    companion object {
        const val TAG = "MainFragmentPresenter"
    }
}
