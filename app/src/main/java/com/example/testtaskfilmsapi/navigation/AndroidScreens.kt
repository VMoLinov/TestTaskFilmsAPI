package com.example.testtaskfilmsapi.navigation

import com.example.testtaskfilmsapi.view.details.DetailsFragment
import com.example.testtaskfilmsapi.view.main.MainFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object AndroidScreens {

    class MainScreen : SupportAppScreen() {
        override fun getFragment() = MainFragment()
    }

    class DetailsScreen : SupportAppScreen() {
        override fun getFragment() = DetailsFragment()
    }
}