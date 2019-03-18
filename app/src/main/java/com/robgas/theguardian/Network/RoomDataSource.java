package com.robgas.theguardian.Network;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.robgas.theguardian.Models.FeedItem;
import com.robgas.theguardian.SoloLearnApplication;
import com.robgas.theguardian.Utils.SoloUtils;

public class RoomDataSource extends PageKeyedDataSource<Integer, FeedItem> {
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, FeedItem> callback) {
        callback.onResult(SoloUtils.getFeedItemListFromOfflineEntity(SoloLearnApplication.getApplicationInstance()
                .appDb.offlineDao().getOfflineList()), null, null);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, FeedItem> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, FeedItem> callback) {

    }

}
