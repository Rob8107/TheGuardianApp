package com.robgas.theguardian.Network;

import com.robgas.theguardian.SoloLearnApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static final String BASE_URL = "https://content.guardianapis.com";
    public static final String GUARDIAN_KEY = "cb273d4d-36fc-4a1e-99b3-f9d5d199c9a3";
    private static Retrofit sRetrofitInstance = null;
    private static ApiInterface sApiInterface = null;

    private RetrofitClient() {
        // this class is not publicly accessible
    }


    private static Retrofit getRestClient() {

        if (sRetrofitInstance != null) {
            return sRetrofitInstance;
        }

        int cacheSize = 10 * 1024 * 1024; // 10 MB
        Cache cache = new Cache(SoloLearnApplication.getApplicationInstance().getCacheDir(), cacheSize);

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.readTimeout(10, TimeUnit.SECONDS);
        builder.connectTimeout(10, TimeUnit.SECONDS);
        builder.cache(cache);

        builder.addInterceptor(chain -> {
            Request.Builder builder1 = chain.request().newBuilder()
                    .addHeader("api-key", GUARDIAN_KEY);

            if (SoloLearnApplication.isNetworkAvailable()) {
                builder1.addHeader("Cache-Control", "public, max-age=" + 0);
            } else {
                builder1.addHeader("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24); // 1 day
            }

            Request request = builder1.build();
            return chain.proceed(request);
        });

        OkHttpClient client = builder.build();

        sRetrofitInstance = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return sRetrofitInstance;
    }

    public static ApiInterface request() {
        if (sApiInterface != null) {
            return sApiInterface;
        }

        sApiInterface = getRestClient().create(ApiInterface.class);
        return sApiInterface;
    }


}
