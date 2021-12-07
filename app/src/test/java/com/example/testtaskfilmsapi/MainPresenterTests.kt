package com.example.testtaskfilmsapi

import com.example.testtaskfilmsapi.model.Films
import com.example.testtaskfilmsapi.remote.FilmsRepo
import com.example.testtaskfilmsapi.view.main.MainFragmentPresenter
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.terrakok.cicerone.Router

class MainPresenterTests {

    private lateinit var presenter: MainFragmentPresenter

    @Mock
    private lateinit var data: FilmsRepo

    @Mock
    private lateinit var router: Router

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainFragmentPresenter(data, router)
    }

    @Test
    fun onFirstAttachTest() {
        val callback: Callback<Films> = object : Callback<Films> {
            override fun onResponse(call: Call<Films>, response: Response<Films>) = Unit
            override fun onFailure(call: Call<Films>, t: Throwable) = Unit
        }
        presenter.loadData()
//        val call = mock(Callback::class.java) as Callback<Films>
        verify(data, times(1)).getFilms(callback)
    }
}
