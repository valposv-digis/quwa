package com.digis.quwa.test.app.di.providers;

import com.digis.quwa.test.data.auth.AuthApi;

import javax.inject.Inject;
import javax.inject.Provider;

import retrofit2.Retrofit;

public class AuthApiProvider implements Provider<AuthApi> {

    private final Retrofit retrofit;

    @Inject
    public AuthApiProvider(Retrofit retrofit) {
        this.retrofit = retrofit;
    }

    @Override
    public AuthApi get() {
        return retrofit.create(AuthApi.class);
    }
}
