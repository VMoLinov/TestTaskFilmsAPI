package com.example.testtaskfilmsapi.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskfilmsapi.databinding.ItemRecyclerMainBinding
import com.example.testtaskfilmsapi.view.imageloader.GlideImageLoader
import com.example.testtaskfilmsapi.view.imageloader.ImageLoader

class MainAdapter(
    private val presenter: MainFragmentPresenter.ItemsListPresenter,
    private val imageLoader: ImageLoader = GlideImageLoader()
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecyclerMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply { itemView.setOnClickListener { presenter.itemCLickListener?.invoke(this) } }
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        presenter.bindView(holder.apply { pos = position })
    }

    inner class ViewHolder(private val binding: ItemRecyclerMainBinding) :
        RecyclerView.ViewHolder(binding.root), MainItemView {

        override fun showName(name: String) {
            binding.filmName.text = name
        }

        override fun loadImage(url: String?) {
            imageLoader.load(url, binding.filmImage)
        }

        override var pos: Int = -1
    }
}
