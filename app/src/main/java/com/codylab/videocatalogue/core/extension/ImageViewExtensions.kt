package com.codylab.videocatalogue.core.extension

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.codylab.videocatalogue.R


fun ImageView.loadImageFromUrl(url: String, radius: Int = 0) {
    var options = RequestOptions()
        .placeholder(R.drawable.ic_image_black_24dp)

    if (radius > 0) {
        options = options.transforms(CenterCrop(), RoundedCorners(radius))
    }
    Glide.with(context).load(url).apply(options).into(this)
}