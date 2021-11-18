package com.example.testtaskfilmsapi.view.imageloader

import android.graphics.drawable.Drawable
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.testtaskfilmsapi.R

class GlideImageLoader : ImageLoader {

    private val handler = Handler(Looper.getMainLooper())

    override fun load(url: String?, imageView: ImageView) {
        if (!url.isNullOrEmpty()) {
            Glide.with(imageView.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        handler.post {
                            Glide.with(imageView.context)
                                .load(R.drawable.ic_load_error_vector)
                                .override(200, 200)
                                .centerInside()
                                .into(imageView)
                        }
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .override(550, 550)
                .centerCrop()
                .into(imageView)
        } else {
            Glide.with(imageView.context)
                .load(R.drawable.ic_load_error_vector)
                .fitCenter()
                .into(imageView)
        }
    }
}
