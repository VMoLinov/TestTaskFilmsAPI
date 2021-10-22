package com.example.testtaskfilmsapi.view.main

interface MainItemView : IItemView {

    fun showName(name: String)
    fun loadImage(url: String?)
}