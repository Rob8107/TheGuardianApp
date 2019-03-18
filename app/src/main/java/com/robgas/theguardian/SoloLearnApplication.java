package com.robgas.theguardian;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

import com.robgas.theguardian.Database.DBService;
import com.robgas.theguardian.Network.ApiInterface;
import com.robgas.theguardian.Network.RetrofitClient;
import com.robgas.theguardian.Utils.SharedPreferencesUtils;


public class SoloLearnApplication extends Application {

    private static SoloLearnApplication instance;
    public DBService appDb;
    private SharedPreferencesUtils sharedPreferencesUtils;

    public static SoloLearnApplication getApplicationInstance() {
        return instance;
    }

    public static boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) instance.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public SharedPreferencesUtils getSharedPreferencesUtils() {
        return sharedPreferencesUtils;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("ChannelId",
                    "ChannelName", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager service = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            service.createNotificationChannel(channel);
        }

    }

    public ApiInterface getApiInterface() {
        return RetrofitClient.request();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appDb = DBService.getDataBase(instance);
        createNotificationChannel();
        sharedPreferencesUtils = SharedPreferencesUtils.getInstance();
    }
}
