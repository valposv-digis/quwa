package com.digis.quwa.test.app.di.providers;

import com.digis.quwa.test.BuildConfig;
import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Provider;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitProvider implements Provider<Retrofit> {

    private final OkHttpClient okHttpClient;
    private final Gson gson;

    @Inject
    public RetrofitProvider(OkHttpClient okHttpClient, Gson gson) {
        this.okHttpClient = okHttpClient;
        this.gson = gson;
    }

    @Override
    public Retrofit get() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }
}
