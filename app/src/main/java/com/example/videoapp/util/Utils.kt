package com.example.videoapp.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.videoapp.R

object Utils {
    private fun getProgressDrawable(context: Context): CircularProgressDrawable {
        return CircularProgressDrawable(context).apply {
            strokeWidth = 10f
            centerRadius = 50f
            start()
        }
    }

    private fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
        val options = RequestOptions()
            .placeholder(progressDrawable)
            .error(R.mipmap.ic_launcher)
        Glide.with(context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("android:imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        view.loadImage("http://image.tmdb.org/t/p/w342/$url", getProgressDrawable(view.context))
    }
}