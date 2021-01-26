package com.digis.quwa.test.data.auth;

import com.google.gson.annotations.SerializedName;

public class AuthData {

    @SerializedName("token") private final String token;
    @SerializedName("app_init") private final AppInitData appInitData;

    public AuthData(String token, AppInitData appInitData) {
        this.token = token;
        this.appInitData = appInitData;
    }

    public String getToken() {
        return token;
    }

    public AppInitData getAppInitData() {
        return appInitData;
    }
}
