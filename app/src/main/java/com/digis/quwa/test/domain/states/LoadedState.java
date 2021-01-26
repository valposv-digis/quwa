package com.digis.quwa.test.domain.states;

public class LoadedState<T> extends State {

    private final T data;

    public LoadedState(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
