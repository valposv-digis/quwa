package com.digis.quwa.test.app.base;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends AndroidViewModel {

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

    protected Bundle args;

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public void onStart() { }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    public void setArgs(Bundle bundle) {
        this.args = bundle;
    }

    protected void runRxTask(Disposable task) {
        compositeDisposable.add(task);
    }

    protected void handleThrowable(Throwable throwable) {
        errorMessage.setValue("Stub");
    }

    protected String getString(int resourceId) {
        return getApplication().getString(resourceId);
    }
}
