package com.example.testtaskfilmsapi.view.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testtaskfilmsapi.databinding.ItemRecyclerMainFilmBinding
import com.example.testtaskfilmsapi.databinding.ItemRecyclerMainGenreBinding
import com.example.testtaskfilmsapi.model.Film
import com.example.testtaskfilmsapi.view.imageloader.GlideImageLoader
import com.example.testtaskfilmsapi.view.imageloader.ImageLoader
import com.google.android.material.chip.Chip

class MainAdapter(
    private val presenter: MainFragmentPresenter.FilmsListPresenter,
    private val imageLoader: ImageLoader = GlideImageLoader()
) : RecyclerView.Adapter<MainAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            GENRES -> {
                GenreViewHolder(
                    ItemRecyclerMainGenreBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ).apply { button.setOnClickListener { presenter.itemCLickListener?.invoke(this) } }
            }
            FILMS -> {
                FilmsViewHolder(
                    ItemRecyclerMainFilmBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ).apply { itemView.setOnClickListener { presenter.itemCLickListener?.invoke(this) } }
            }
            else -> {
                throw IllegalStateException()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (presenter.data[position]) {
            is String -> GENRES
            is Film -> FILMS
            else -> throw IllegalStateException()
        }
    }

    override fun getItemCount(): Int = presenter.getCount()

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        presenter.bind(holder.apply { pos = position })
    }

    abstract inner class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        abstract var pos: Int
        abstract fun loadString(text: String)
    }

    inner class FilmsViewHolder(private val binding: ItemRecyclerMainFilmBinding) :
        BaseViewHolder(binding.root) {
        override var pos: Int = -1

        override fun loadString(text: String) {
            binding.filmName.text = text
        }

        fun loadImage(url: String?) {
            imageLoader.load(url, binding.filmImage)
        }
    }

    inner class GenreViewHolder(private val binding: ItemRecyclerMainGenreBinding) :
        BaseViewHolder(binding.root) {
        override var pos: Int = -1
        val button: Chip = binding.genre

        override fun loadString(text: String) {
            binding.genre.text = text
        }

        fun clearChip() {
            button.isChecked = false
        }
    }

    companion object {
        const val FILMS = 1 //span weight
        const val GENRES = 2 //full string
    }
}
