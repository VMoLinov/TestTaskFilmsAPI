package com.example.testtaskfilmsapi.view.main

import com.example.testtaskfilmsapi.model.Film
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainFragmentView : MvpView {

    fun renderData(data: Film)
}
