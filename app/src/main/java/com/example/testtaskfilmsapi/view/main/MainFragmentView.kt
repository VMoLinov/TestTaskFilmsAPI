package com.example.testtaskfilmsapi.view.main

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

@AddToEndSingle
interface MainFragmentView : MvpView {

    fun renderData()

    fun notifyItemsExclude(position: Int, size: IntRange)

    fun removeRange(range: List<Int>)

    fun addRange(range: List<Int>)
}
