package com.digis.quwa.test.data.auth;

import com.digis.quwa.test.data.SharedPrefsCache;

import javax.inject.Inject;

public class AuthCache extends SharedPrefsCache<AuthData> {

    @Inject
    public AuthCache() {
        super();
    }

    @Override
    protected String getEntityKey() {
        return "AuthCache.KEY_AUTH_DATA";
    }

    @Override
    protected Class<AuthData> getEntityClass() {
        return AuthData.class;
    }
}
