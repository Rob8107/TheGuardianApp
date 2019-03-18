package com.robgas.theguardian.Network;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.robgas.theguardian.Models.FeedItem;
import com.robgas.theguardian.Network.model.FeedResponse;
import com.robgas.theguardian.Network.model.Result;
import com.robgas.theguardian.SoloLearnApplication;
import com.robgas.theguardian.Utils.SharedPreferencesUtils;
import com.robgas.theguardian.Utils.SoloUtils;

import java.util.ArrayList;
import java.util.List;

public class FeedDataSource extends PageKeyedDataSource<Integer, FeedItem> {

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, FeedItem> callback) {
        callback.onResult(getFeed(1), null, 2);
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, FeedItem> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, FeedItem> callback) {
        callback.onResult(getFeed(params.key), params.key + 1);
    }

    // worker thread
    private List<FeedItem> getFeed(final int pageNumber) {
        List<FeedItem> tmpList = new ArrayList<>();
        try {
            FeedResponse feedResponse = SoloLearnApplication.getApplicationInstance().getApiInterface().getFeed(pageNumber).execute().body();

            FeedItem feedItem;
            if (feedResponse != null) {
                for (Result result : feedResponse.response.results) {
                    feedItem = new FeedItem(
                            result.id == null ? "" : result.id,
                            result.sectionName == null ? "" : result.sectionName,
                            result.webTitle == null ? "" : result.webTitle,
                            result.fields == null ? "" : result.fields.thumbnail == null ? "" : result.fields.thumbnail
                    );
                    tmpList.add(feedItem);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (pageNumber == 1) {
            SharedPreferencesUtils.getInstance().setFeedsId(SoloUtils.getFeedItemIDListFromFeedItem(tmpList));
        }
        return tmpList;
    }

}

