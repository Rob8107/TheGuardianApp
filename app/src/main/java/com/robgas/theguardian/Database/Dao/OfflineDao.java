package com.robgas.theguardian.Database.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.Nullable;

import com.robgas.theguardian.Database.Entity.OfflineEntity;
import com.robgas.theguardian.Models.FeedItem;

import java.util.List;

@Dao
public interface OfflineDao {

    @Query("SELECT * FROM offline_table")
    LiveData<List<OfflineEntity>> getOfflineFeedList();

    @Query("SELECT * FROM offline_table")
    DataSource.Factory<Integer, FeedItem> getOfflinePagedList();

    @Query("SELECT * FROM offline_table")
    List<OfflineEntity> getOfflineList();

    @Nullable
    @Query("SELECT * FROM offline_table WHERE id=:feedId")
    OfflineEntity getOfflineFeedById(String feedId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOfflineEntity(OfflineEntity pinEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateOfflineEntity(OfflineEntity pinEntity);

    @Delete()
    void deleteOfflineEntity(OfflineEntity pinEntity);
}
