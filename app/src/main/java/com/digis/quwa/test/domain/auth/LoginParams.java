package com.digis.quwa.test.domain.auth;

import com.google.gson.annotations.SerializedName;

public class LoginParams {

    @SerializedName("email") private final String email;
    @SerializedName("password") private final String password;

    public LoginParams(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
