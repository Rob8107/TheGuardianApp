package com.robgas.theguardian.Utils;

import android.support.annotation.NonNull;

import com.robgas.theguardian.Database.Entity.OfflineEntity;
import com.robgas.theguardian.Database.Entity.PinEntity;
import com.robgas.theguardian.Models.FeedItem;

import java.util.ArrayList;
import java.util.List;

public class SoloUtils {

    @NonNull
    public static PinEntity getFeedEntityFromFeedItem(@NonNull FeedItem feedItem) {
        PinEntity returnValue = new PinEntity();
        returnValue.setId(feedItem.getId());
        returnValue.setImage(feedItem.getImage());
        returnValue.setCategory(feedItem.getCategory());
        returnValue.setPined(feedItem.isPined());
        return returnValue;
    }

    @NonNull
    public static OfflineEntity getOfflineEntityFromFeedItem(@NonNull FeedItem feedItem) {
        OfflineEntity returnValue = new OfflineEntity();
        returnValue.setId(feedItem.getId());
        returnValue.setImage(feedItem.getImage());
        returnValue.setCategory(feedItem.getCategory());
        returnValue.setPined(feedItem.isPined());
        return returnValue;
    }

    @NonNull
    public static FeedItem getFeedItemFromFeedEntity(@NonNull PinEntity pinEntity) {
        FeedItem returnValue = new FeedItem();
        returnValue.setId(pinEntity.getId());
        returnValue.setImage(pinEntity.getImage());
        returnValue.setCategory(pinEntity.getCategory());
        returnValue.setPined(pinEntity.isPined());
        return returnValue;
    }

    @NonNull
    public static List<FeedItem> getFeedItemListFromOfflineEntity(@NonNull List<OfflineEntity> pinEntity) {
        List<FeedItem> returnValue = new ArrayList<>();
        for (OfflineEntity entity : pinEntity) {
            FeedItem feedItem = new FeedItem();
            feedItem.setId(entity.getId());
            feedItem.setImage(entity.getImage());
            feedItem.setCategory(entity.getCategory());
            feedItem.setPined(entity.isPined());
            returnValue.add(feedItem);
        }
        return returnValue;
    }

    @NonNull
    public static List<FeedItem> getFeedItemFromFeedEntity(@NonNull List<PinEntity> pinEntity) {
        List<FeedItem> returnValue = new ArrayList<>();
        for (PinEntity entity : pinEntity) {
            FeedItem feedItem = new FeedItem();
            feedItem.setId(entity.getId());
            feedItem.setImage(entity.getImage());
            feedItem.setCategory(entity.getCategory());
            feedItem.setPined(entity.isPined());
            returnValue.add(feedItem);
        }
        return returnValue;
    }

    @NonNull
    public static String[] getFeedItemIDListFromFeedItem(List<FeedItem> feedItems) {
        String[] strings = new String[feedItems.size()];
        for (int i = 0; i < feedItems.size(); i++) {
            strings[i] = feedItems.get(i).getId();
        }
        return strings;
    }

    public static List<FeedItem> getMissedFeedItem(List<String> oldFeedItem, List<FeedItem> newFeedItem) {
        List<FeedItem> feedItems = new ArrayList<>();
        int counter;
        for (int i = 0; i < newFeedItem.size(); i++) {
            counter = 0;
            for (int j = 0; j < oldFeedItem.size(); j++) {
                if (newFeedItem.get(i).getId().equals(oldFeedItem.get(j))) {
                    counter++;
                }
            }
            if (counter == 0) {
                feedItems.add(newFeedItem.get(i));
            }
        }
        return feedItems;
    }
}
