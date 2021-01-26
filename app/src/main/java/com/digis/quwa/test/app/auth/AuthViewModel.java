package com.digis.quwa.test.app.auth;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.digis.quwa.test.R;
import com.digis.quwa.test.app.base.BaseViewModel;
import com.digis.quwa.test.app.utils.SingleLiveEvent;
import com.digis.quwa.test.domain.auth.LoginParams;
import com.digis.quwa.test.domain.auth.LoginUseCase;
import com.digis.quwa.test.domain.states.ErrorState;
import com.digis.quwa.test.domain.states.InitialLoadingState;
import com.digis.quwa.test.domain.states.LoadedEmptyState;
import com.digis.quwa.test.domain.states.State;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;


public class AuthViewModel extends BaseViewModel {

    private final LoginUseCase loginUseCase;

    @Inject
    public AuthViewModel(@NonNull Application application, LoginUseCase loginUseCase) {
        super(application);
        this.loginUseCase = loginUseCase;
    }

    public SingleLiveEvent<Void> loggedInEvent = new SingleLiveEvent<>();

    public void login(String email, String password) {

        if (!isEmailValid(email) || !isPasswordValid(password)) {
            errorMessage.setValue(getString(R.string.err_check_email_and_password));
            return;
        }

        runRxTask(
                loginUseCase.execute(new LoginParams(email, password)).subscribe(this::handleLoginState)
        );
    }

    public void onForgotPasswordBtnClick() {
        errorMessage.setValue(getString(R.string.a_forgot_password));
    }

    private void handleLoginState(State state) {
        if (state instanceof InitialLoadingState) {
            loading.setValue(true);
        } else if (state instanceof LoadedEmptyState) {
            loading.setValue(false);
            loggedInEvent.call();
        } else if (state instanceof ErrorState) {
            loading.setValue(false);
            errorMessage.setValue(((ErrorState) state).getThrowable().getMessage());
        }
    }

    private boolean isEmailValid(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isPasswordValid(String password) {
        return !password.isEmpty();
    }
}
