package com.example.testtaskfilmsapi.view.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testtaskfilmsapi.App
import com.example.testtaskfilmsapi.databinding.FragmentMainBinding
import com.example.testtaskfilmsapi.model.FilmsRepoImpl
import com.example.testtaskfilmsapi.navigation.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class MainFragment : MvpAppCompatFragment(), MainFragmentView, BackButtonListener {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val presenter by moxyPresenter {
        MainFragmentPresenter(
            FilmsRepoImpl(),
            App.instance.router
        )
    }
    private val adapter by lazy { MainAdapter(presenter.itemsListPresenter) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun renderData() {
        binding.recyclerMain.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerMain.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
