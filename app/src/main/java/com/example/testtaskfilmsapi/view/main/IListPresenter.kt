package com.example.testtaskfilmsapi.view.main

interface IMainListPresenter : IListPresenter<MainItemView>

interface IListPresenter<V> {
    var itemCLickListener: ((V) -> Unit)?
    fun bindView(view: V)
    fun getCount(): Int
}

interface IItemView {
    var pos: Int
}
