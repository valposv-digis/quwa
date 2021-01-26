package com.digis.quwa.test.app.di;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import toothpick.Scope;
import toothpick.Toothpick;

public class ToothpickUtils {

    public static <T extends ViewModel> T provideViewModel(
            AppCompatActivity activity,
            Class<T> viewModelClass
    ) {
        return provideViewModel(getAppScope(), activity, viewModelClass);
    }

    public static <T extends ViewModel> T provideViewModel(
            Fragment fragment,
            Class<T> viewModelClass
    ) {
        return provideViewModel(getAppScope(), fragment, viewModelClass);
    }

    public static <T extends ViewModel> T provideViewModel(
            Scope scope,
            AppCompatActivity activity,
            Class<T> viewModelClass
    ) {
        return ViewModelProviders.of(activity, new ToothpickViewModelProviderFactory(scope)).get(viewModelClass);
    }

    public static <T extends ViewModel> T provideViewModel(
            Scope scope,
            Fragment fragment,
            Class<T> viewModelClass
    ) {
        return ViewModelProviders.of(fragment, new ToothpickViewModelProviderFactory(scope)).get(viewModelClass);
    }

    public static Scope getAppScope() {
        return Toothpick.openScope(Scopes.APP_SCOPE);
    }
}
