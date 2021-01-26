package com.digis.quwa.test.app.main;

import android.app.Application;

import androidx.annotation.NonNull;

import com.digis.quwa.test.app.base.BaseViewModel;
import com.digis.quwa.test.app.utils.SingleLiveEvent;
import com.digis.quwa.test.data.auth.AuthRepository;

import javax.inject.Inject;

public class MainViewModel extends BaseViewModel {

    @Inject
    public MainViewModel(@NonNull Application application, AuthRepository authRepo) {
        super(application);
        if (!authRepo.isAuthorized()) {
            openAuthScreenAction.call();
        } else {
            openProjectsListScreenAction.call();
        }
    }

    public SingleLiveEvent<Void> openAuthScreenAction = new SingleLiveEvent<>();
    public SingleLiveEvent<Void> openProjectsListScreenAction = new SingleLiveEvent<>();
}
