package com.example.testtaskfilmsapi.view.details

import com.example.testtaskfilmsapi.model.Film
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class DetailsFragmentPresenter(
    private val data: Film?,
    private val router: Router
) : MvpPresenter<DetailsFragmentView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        data?.apply {
            viewState.setImage(image_url)
            viewState.setName(name)
            viewState.setYear(year.toString())
            viewState.setRating(rating.toString())
            viewState.setDescription(description)
            viewState.setActionBar(localized_name)
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}