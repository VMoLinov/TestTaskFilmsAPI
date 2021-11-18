package com.example.testtaskfilmsapi.view.main

interface ListPresenter<V> {

    var itemCLickListener: ((V) -> Unit)?
    fun bind(view: V)
    fun getCount(): Int
}
