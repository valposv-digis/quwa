package com.digis.quwa.test.app.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

public class ViewUtils {

    public static void loadImage(ImageView imageView, String imageUrl, int placeholderResId) {
        Glide.with(imageView.getContext())
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade(new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()))
                .placeholder(placeholderResId)
                .circleCrop()
                .into(imageView);
    }
}
