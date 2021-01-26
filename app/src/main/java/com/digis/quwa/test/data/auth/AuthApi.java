package com.digis.quwa.test.data.auth;

import com.digis.quwa.test.domain.auth.LoginParams;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthApi {

    @POST("auth/login")
    Observable<AuthData> login(@Body LoginParams loginParams);
}
