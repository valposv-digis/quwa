package com.digis.quwa.test.domain;

import com.digis.quwa.test.domain.states.State;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T> {

    private final Scheduler ioScheduler, uiScheduler;

    public UseCase() {
        this.ioScheduler = Schedulers.io();
        this.uiScheduler = AndroidSchedulers.mainThread();
    }

    protected abstract Observable<State> createObservable(T params);

    public Observable<State> execute(T params) {
        return createObservable(params)
                .subscribeOn(ioScheduler)
                .observeOn(uiScheduler);
    }
}
