package com.robgas.theguardian.Views;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.robgas.theguardian.R;

import de.hdodenhof.circleimageview.CircleImageView;


public class CircleNetworkImageView extends CircleImageView {
    public CircleNetworkImageView(Context context) {
        super(context);
    }

    public CircleNetworkImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleNetworkImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void loadImage(String url) {
        Glide.with(this)
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .dontAnimate()
                .centerCrop()
                .error(R.drawable.image_placeholder)
                .into(this);
    }
}
