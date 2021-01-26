package com.digis.quwa.test.domain.auth;

import com.digis.quwa.test.data.auth.AuthRepository;
import com.digis.quwa.test.domain.UseCase;
import com.digis.quwa.test.domain.states.State;

import javax.inject.Inject;

import io.reactivex.Observable;

public class LoginUseCase extends UseCase<LoginParams> {

    private final AuthRepository authRepository;

    @Inject
    public LoginUseCase(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    protected Observable<State> createObservable(LoginParams params) {
        return authRepository.login(params);
    }
}
