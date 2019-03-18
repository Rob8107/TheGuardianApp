package com.robgas.theguardian.Network;

import android.arch.paging.DataSource;

import com.robgas.theguardian.Models.FeedItem;

public class RoomDataSourceFactory extends DataSource.Factory<Integer, FeedItem> {
    @Override
    public DataSource<Integer, FeedItem> create() {
        return new RoomDataSource();
    }
}
