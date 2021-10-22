package com.example.testtaskfilmsapi

import com.example.testtaskfilmsapi.navigation.AndroidScreens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router

class MainPresenter(private val router: Router) : MvpPresenter<MainView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(AndroidScreens.MainScreen())
    }

    fun backPressed() {
        router.exit()
    }
}
