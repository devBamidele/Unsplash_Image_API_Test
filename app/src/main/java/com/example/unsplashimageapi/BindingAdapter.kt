package com.example.unsplashimageapi

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

/**
 * Uses the [Coil library] to load an image by URL into an [ImageView]
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        // Load the image in the background using Coil
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        imgView.load(imgUri) {

            // Effect that can be applied to the image
            crossfade(true)
            crossfade(400)

            // The loading animation
            placeholder(R.drawable.loading_animation)

            // The error image used when coil is unable to get the iamge
            error(R.drawable.ic_broken_image)

        }

    }
}