package com.robgas.theguardian.Utils;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.robgas.theguardian.SoloLearnApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;
import static com.robgas.theguardian.Utils.SharedPreferencesUtils.PreferencesConstants.FeedsIdKey;

public class SharedPreferencesUtils {

    private static final SharedPreferencesUtils instance = new SharedPreferencesUtils();
    private SharedPreferences prefs;

    private SharedPreferencesUtils() {
        prefs = SoloLearnApplication.getApplicationInstance().getSharedPreferences("soloPref", MODE_PRIVATE);
    }

    public static SharedPreferencesUtils getInstance() {
        return instance;
    }

    public void setFeedsId(@NonNull String[] feedsId) {
        prefs.edit().putStringSet("FeedsIds", new HashSet<>(Arrays.asList(feedsId))).apply();
    }

    public void setFeedId(@NonNull String id) {
        List<String> list = getFeedsIds();
        list.add(id);
        String array[] = new String[list.size()];
        list.toArray(array);
        setFeedsId(array);
    }

    @NonNull
    public List<String> getFeedsIds() {
        return new ArrayList<>(Objects.requireNonNull(prefs.getStringSet(FeedsIdKey, null)));
    }

    static class PreferencesConstants {
        static String FeedsIdKey = "FeedsIds";
    }
}
