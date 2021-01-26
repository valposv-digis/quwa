package com.digis.quwa.test.data.auth;

import com.digis.quwa.test.domain.auth.LoginParams;
import com.digis.quwa.test.domain.states.ErrorState;
import com.digis.quwa.test.domain.states.InitialLoadingState;
import com.digis.quwa.test.domain.states.LoadedEmptyState;
import com.digis.quwa.test.domain.states.State;

import javax.inject.Inject;

import io.reactivex.Observable;

public class AuthRepository {

    private final AuthApi authApi;
    private final AuthCache authCache;

    @Inject
    public AuthRepository(AuthApi authApi, AuthCache authCache) {
        this.authApi = authApi;
        this.authCache = authCache;
    }

    public Observable<State> login(LoginParams loginParams) {
        return Observable.concat(
                Observable.just(new InitialLoadingState()),
                authApi.login(loginParams)
                        .doOnNext(this::cacheAuthData)
                        .flatMap(authData -> Observable.just(new LoadedEmptyState()))
        ).onErrorReturn(ErrorState::new);
    }

    public boolean isAuthorized() {
        return authCache.getEntity() != null;
    }

    private void cacheAuthData(AuthData authData) {
        authCache.setEntity(authData);
    }
}
