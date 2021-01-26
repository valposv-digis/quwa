package com.digis.quwa.test.data;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import javax.inject.Inject;

public abstract class SharedPrefsCache<T> {

    protected abstract String getEntityKey();
    protected abstract Class<T> getEntityClass();

    @Inject protected Gson gson;
    @Inject protected SharedPreferences sharedPreferences;

    public T getEntity() {
        String cachedEntityJson = sharedPreferences.getString(getEntityKey(), null);
        if (cachedEntityJson == null) {
            return null;
        }

        return fromJson(cachedEntityJson);
    }

    public void setEntity(T entity) {
        sharedPreferences.edit().putString(getEntityKey(), gson.toJson(entity)).apply();
    }

    public void clear() {
        setEntity(null);
    }

    private T fromJson(String json) {
        return gson.fromJson(json, getEntityClass());
    }
}