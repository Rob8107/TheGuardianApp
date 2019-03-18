package com.robgas.theguardian.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;

import com.robgas.theguardian.MainActivity;
import com.robgas.theguardian.Models.FeedItem;
import com.robgas.theguardian.Network.model.FeedResponse;
import com.robgas.theguardian.Network.model.Result;
import com.robgas.theguardian.R;
import com.robgas.theguardian.SoloLearnApplication;
import com.robgas.theguardian.Utils.SoloUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BackgroundCheckerScheduler extends JobService {

    private android.app.NotificationManager notificationManger = (NotificationManager) SoloLearnApplication.getApplicationInstance().getSystemService(Context.NOTIFICATION_SERVICE);
    private List<FeedItem> tmpList;
    private Handler handler;
    private int delayMillis = 30000;

    @Override
    public boolean onStartJob(JobParameters params) {
        notificationManger.notify(1, createNotification());
        handler = new Handler();
        scheduleGetFeed();
        return true;
    }

    private Notification createNotification(FeedItem feedItem) {
        Intent notificationIntent = new Intent(SoloLearnApplication.getApplicationInstance(), MainActivity.class);
        notificationIntent.putExtra(MainActivity.FeedItemId, feedItem);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                SoloLearnApplication.getApplicationInstance(),
                1,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        return new NotificationCompat.Builder(SoloLearnApplication.getApplicationInstance(), "ChannelId")
                .setContentTitle(feedItem.getCategory())
                .setContentText(feedItem.getTitle())
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.logo)
                .build();
    }

    private Notification createNotification() {
        Intent notificationIntent = new Intent(SoloLearnApplication.getApplicationInstance(), MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(
                SoloLearnApplication.getApplicationInstance(),
                1,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);


        return new NotificationCompat.Builder(SoloLearnApplication.getApplicationInstance(), "ChannelId")
                .setContentTitle("Solo Learn App Now Searching new Items")
                .setContentIntent(pendingIntent)
                .setAutoCancel(false)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.logo)
                .build();
    }

    public void scheduleGetFeed() {

        handler.postDelayed(new Runnable() {
            public void run() {
                searchNewItems();
                handler.postDelayed(this, delayMillis);
            }
        }, delayMillis);
    }

    private void searchNewItems() {
        SoloLearnApplication.getApplicationInstance().getApiInterface().getFeed(1).enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                tmpList = new ArrayList<>();
                FeedResponse feedResponse = response.body();

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
                List<FeedItem> feedItems = SoloUtils.getMissedFeedItem(SoloLearnApplication.getApplicationInstance().getSharedPreferencesUtils().getFeedsIds(), tmpList);
                if (feedItems.size() > 0) {
                    for (FeedItem feedItem1 : feedItems) {
                        SoloLearnApplication.getApplicationInstance().getSharedPreferencesUtils().setFeedId(feedItem1.getId());
                        notificationManger.notify((int) (Math.random() * 100), createNotification(feedItem1));
                    }
                }

            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        notificationManger.cancelAll();
        return false;
    }
}
