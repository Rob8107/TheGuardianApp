package com.robgas.theguardian.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.robgas.theguardian.Database.Dao.OfflineDao;
import com.robgas.theguardian.Database.Dao.PinDao;
import com.robgas.theguardian.Database.Entity.OfflineEntity;
import com.robgas.theguardian.Database.Entity.PinEntity;

@Database(entities = {PinEntity.class, OfflineEntity.class}, version = 1, exportSchema = false)
public abstract class DBService extends RoomDatabase {
    private static DBService INSTANCE = null;

    public static DBService getDataBase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder
                    (context, DBService.class, "solo.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public abstract PinDao feedDao();

    public abstract OfflineDao offlineDao();
}
