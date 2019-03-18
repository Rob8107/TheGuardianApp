package com.robgas.theguardian.Database.Entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "offline_table")
public class OfflineEntity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    String id = "";
    @ColumnInfo(name = "title")
    String title;
    @ColumnInfo(name = "image")
    String image;
    @ColumnInfo(name = "category")
    String category;
    @ColumnInfo(name = "isPined")
    boolean isPined;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isPined() {
        return isPined;
    }

    public void setPined(boolean pined) {
        isPined = pined;
    }
}
