package com.robgas.theguardian.Fragments;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.robgas.theguardian.Database.Entity.OfflineEntity;
import com.robgas.theguardian.Database.Entity.PinEntity;
import com.robgas.theguardian.Models.FeedItem;
import com.robgas.theguardian.R;
import com.robgas.theguardian.SoloLearnApplication;
import com.robgas.theguardian.Utils.SoloUtils;

public class DetailsFragment extends BaseFragment {

    public static final String EXTRA_FEED_ITEM = "extra_feed_item";
    private TextView mTitle;
    private Toolbar mToolbar;
    private FeedItem mFeedItem;

    public static DetailsFragment newInstance(Bundle bundle) {
        DetailsFragment fragment = new DetailsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle extras = getArguments();
        if (extras != null) {
            mFeedItem = extras.getParcelable(EXTRA_FEED_ITEM);
        }

        ImageView mImage = view.findViewById(R.id.image);
        final ImageView mOfflineImage = view.findViewById(R.id.offline_image);
        final ImageView mPinImage = view.findViewById(R.id.pin_image);

        checkPinStatus(mPinImage);
        checkOfflineStatus(mOfflineImage);

        mPinImage.setOnClickListener(v -> {
            if (mFeedItem.isPined()) {
                mFeedItem.setPined(!mFeedItem.isPined());
                mPinImage.setSelected(mFeedItem.isPined());
                SoloLearnApplication.getApplicationInstance().appDb.feedDao().deletePinEntity(SoloUtils.getFeedEntityFromFeedItem(mFeedItem));
            } else {
                mFeedItem.setPined(!mFeedItem.isPined());
                mPinImage.setSelected(mFeedItem.isPined());
                SoloLearnApplication.getApplicationInstance().appDb.feedDao().insertPinEntity(SoloUtils.getFeedEntityFromFeedItem(mFeedItem));
            }

        });

        mOfflineImage.setOnClickListener(v -> {
            if (mFeedItem.isOffline()) {
                mFeedItem.setOffline(!mFeedItem.isOffline());
                mOfflineImage.setImageTintList(ColorStateList.valueOf(Color.WHITE));
                SoloLearnApplication.getApplicationInstance().appDb.offlineDao().deleteOfflineEntity(SoloUtils.getOfflineEntityFromFeedItem(mFeedItem));
            } else {
                mFeedItem.setOffline(!mFeedItem.isOffline());
                mOfflineImage.setImageTintList(ColorStateList.valueOf(Color.BLUE));
                SoloLearnApplication.getApplicationInstance().appDb.offlineDao().insertOfflineEntity(SoloUtils.getOfflineEntityFromFeedItem(mFeedItem));
            }

        });


        mTitle = view.findViewById(R.id.description);
        mToolbar = view.findViewById(R.id.toolbar);


        Glide.with(this).load(mFeedItem.getImage()).dontAnimate().listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                mTitle.setText(getString(R.string.large_text));
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                mTitle.setText(getString(R.string.large_text));
                mToolbar.setTitle(mFeedItem.getCategory());
                return false;
            }
        }).into(mImage);
    }

    private void checkPinStatus(ImageView mPinImage) {
        PinEntity pinEntity = SoloLearnApplication.getApplicationInstance().appDb.feedDao().getFeedById(mFeedItem.getId());
        if (pinEntity != null) {
            mFeedItem.setPined(true);
            mPinImage.setSelected(pinEntity.isPined());
        }
    }

    private void checkOfflineStatus(ImageView mOfflineImage) {
        OfflineEntity offlineEntity = SoloLearnApplication.getApplicationInstance().appDb.offlineDao().getOfflineFeedById(mFeedItem.getId());
        if (offlineEntity != null) {
            mFeedItem.setOffline(true);
            mOfflineImage.setImageTintList(ColorStateList.valueOf(Color.BLUE));
        }
    }

    @Override
    public void onNetworkStateChanged(boolean connected) {

    }
}
