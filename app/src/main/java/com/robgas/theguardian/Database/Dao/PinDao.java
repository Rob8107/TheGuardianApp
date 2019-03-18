package com.robgas.theguardian.Database.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.Nullable;

import com.robgas.theguardian.Database.Entity.PinEntity;

import java.util.List;

@Dao
public interface PinDao {

    @Query("SELECT * FROM pin_table")
    LiveData<List<PinEntity>> getFeedList();

    @Query("SELECT * FROM pin_table")
    List<PinEntity> getPinList();

    @Nullable
    @Query("SELECT * FROM pin_table WHERE id=:feedId")
    PinEntity getFeedById(String feedId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPinEntity(PinEntity pinEntity);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePinEntity(PinEntity pinEntity);

    @Delete()
    void deletePinEntity(PinEntity pinEntity);
}
