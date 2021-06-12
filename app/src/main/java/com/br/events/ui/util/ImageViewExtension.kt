package com.br.events.ui.util

import android.widget.ImageView
import com.br.events.R
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String?) {
    if (!url.isNullOrBlank()) {
        Glide.with(this)
            .load(url)
            .error(R.drawable.error)
            .into(this)
    } else {
        this.setImageResource(R.drawable.error)
    }
}