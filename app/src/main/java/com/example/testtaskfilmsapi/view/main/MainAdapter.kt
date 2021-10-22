package com.example.testtaskfilmsapi.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtaskfilmsapi.databinding.ItemMainBinding

class MainAdapter(
    private val presenter: MainFragmentPresenter.ItemsListPresenter
) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMainBinding.inflate(
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

    inner class ViewHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root), MainItemView {

        override fun showName(name: String) {
            binding.name.text = name
        }

        override fun loadImage(url: String?) {
            if (!url.isNullOrEmpty()) {
                Glide.with(binding.image.context)
                    .load(url)
                    .into(binding.image)
            }
        }

        override var pos: Int = -1
    }
}
