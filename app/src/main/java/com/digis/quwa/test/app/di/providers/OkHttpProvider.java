package com.digis.quwa.test.app.di.providers;

import com.digis.quwa.test.app.utils.AuthInterceptor;

import javax.inject.Inject;
import javax.inject.Provider;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttpProvider implements Provider<OkHttpClient> {

    private final AuthInterceptor authInterceptor;

    @Inject
    public OkHttpProvider(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public OkHttpClient get() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .addInterceptor(authInterceptor)
                .build();
    }
}
