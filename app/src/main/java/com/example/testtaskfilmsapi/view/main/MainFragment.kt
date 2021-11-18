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
    private val adapter by lazy { MainAdapter(presenter.filmsListPresenter) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return adapter.getItemViewType(position)
            }
        }
        binding.recyclerMain.layoutManager = layoutManager
        binding.recyclerMain.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun renderData() {
        adapter.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val GRID_SPAN_COUNT = 2
    }
}
