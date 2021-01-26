package com.digis.quwa.test.app.projects.users;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.digis.quwa.test.app.base.BaseViewModel;
import com.digis.quwa.test.domain.entities.User;

import java.util.List;

import javax.inject.Inject;

public class UsersViewModel extends BaseViewModel {

    @Inject
    public UsersViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<User>> users = new MutableLiveData<>();

    @Override
    public void onStart() {
        super.onStart();
        users.setValue(args.getParcelableArrayList(UsersActivity.KEY_USERS));
    }
}
