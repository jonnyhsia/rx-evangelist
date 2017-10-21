package com.citytogo.jonnyhsia.rxevangelist.model;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JonnyHsia on 17/10/21.
 * Retrofit & Service Injection
 */
public class Injection {

    private static final long TIMEOUT_READ = 8;
    private static final long TIMEOUT_WRITE = 8;
    private static final String TAG_HTTP_LOG = "HttpLog";

    private static OkHttpClient sOkHttpClient = new OkHttpClient.Builder()
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(@NonNull String message) {
                    Log.d(TAG_HTTP_LOG, message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(TIMEOUT_READ, TimeUnit.MINUTES)
            .writeTimeout(TIMEOUT_WRITE, TimeUnit.MINUTES)
            .build();

    /**
     * @param baseUrl 注意必须以 / 结尾, 否则会抛出异常
     * @return retrofit 实例
     */
    static Retrofit getRetrofitInstance(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(sOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static StoryService getStoryService() {
        return new StoryService();
    }

    public static PageService getPageService() {
        return new PageService();
    }
}
