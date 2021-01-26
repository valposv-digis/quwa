package com.digis.quwa.test.app.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import toothpick.Scope;

public class ToothpickViewModelProviderFactory implements ViewModelProvider.Factory {

    private final Scope scope;

    public ToothpickViewModelProviderFactory(Scope scope) {
        this.scope = scope;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return scope.getInstance(modelClass);
    }
}
