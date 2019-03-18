package com.robgas.theguardian.Network;

import android.arch.paging.DataSource;

import com.robgas.theguardian.Models.FeedItem;

public class FeedDataSourceFactory extends DataSource.Factory<Integer, FeedItem> {
    private FeedDataSource feedDataSource;

    @Override
    public DataSource<Integer, FeedItem> create() {
        feedDataSource = new FeedDataSource();
        return feedDataSource;
    }

    public void invalidate() {
        feedDataSource.invalidate();
    }
}
