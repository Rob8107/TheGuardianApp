package com.robgas.theguardian.Views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.robgas.theguardian.R;


public class NetworkImageView extends android.support.v7.widget.AppCompatImageView {
    public NetworkImageView(Context context) {
        super(context);
    }

    public NetworkImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NetworkImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void loadImage(String url) {
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .centerCrop()
                .error(R.drawable.image_placeholder)
                .into(this);
    }
}
