package com.robgas.theguardian.Models;

import android.arch.persistence.room.Ignore;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

public class FeedItem implements Parcelable {
    public static final Creator<FeedItem> CREATOR = new Creator<FeedItem>() {
        @Override
        public FeedItem createFromParcel(Parcel in) {
            return new FeedItem(in);
        }

        @Override
        public FeedItem[] newArray(int size) {
            return new FeedItem[size];
        }
    };
    public static DiffUtil.ItemCallback DIFF_CALLBACK = new DiffUtil.ItemCallback<FeedItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull FeedItem oldItem, @NonNull FeedItem newItem) {
            return oldItem.id.equals(newItem.id);
        }

        @Override
        public boolean areContentsTheSame(@NonNull FeedItem oldItem, @NonNull FeedItem newItem) {
            return oldItem == newItem;
        }
    };
    private String id = "";
    private String title = "";
    private String image = "";
    private String category = "";
    private boolean isPined = false;
    @Ignore
    private boolean offline = false;

    public FeedItem() {
    }

    public FeedItem(String id, String category, String title, String image) {
        this.id = id;
        this.category = category;
        this.title = title;
        this.image = image;
    }

    protected FeedItem(Parcel in) {
        id = in.readString();
        title = in.readString();
        image = in.readString();
        category = in.readString();
        isPined = in.readByte() != 0;
    }

    public boolean isPined() {
        return isPined;
    }

    public void setPined(boolean pined) {
        isPined = pined;
    }

    public boolean isOffline() {
        return offline;
    }

    public void setOffline(boolean offline) {
        this.offline = offline;
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(image);
        parcel.writeString(category);
        parcel.writeByte((byte) (isPined ? 1 : 0));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof FeedItem)) return false;

        FeedItem feedItem = (FeedItem) o;

        return getId() != null ? getId().equals(feedItem.getId()) : feedItem.getId() == null;
    }


}
