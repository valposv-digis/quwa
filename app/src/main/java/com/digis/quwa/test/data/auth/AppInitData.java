package com.digis.quwa.test.data.auth;

import com.digis.quwa.test.domain.entities.User;

public class AppInitData {

    private final User user;

    public AppInitData(User user) {
        this.user = user;
    }


    public User getUser() {
        return user;
    }
}
