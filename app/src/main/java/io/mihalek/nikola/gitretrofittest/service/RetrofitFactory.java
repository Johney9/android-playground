package io.mihalek.nikola.gitretrofittest.service;

import io.mihalek.nikola.gitretrofittest.App;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory {

    private RetrofitFactory() {
    }

    public static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static OkHttpClient createOkHttpClient() {
        int cacheSize = 10 * 1024 * 1024;
        final Cache cache = new Cache(App.getAppContext().getCacheDir(), cacheSize);

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        return new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(logging)
                .build();
    }
}
