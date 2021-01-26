package com.digis.quwa.test.app.utils;

public interface ActionListener<T> {

    void onClick(T item);
    void onLongClick(T item);
}
