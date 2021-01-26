package com.digis.quwa.test.app;

import android.app.Application;

import com.digis.quwa.test.app.di.ToothpickUtils;
import com.digis.quwa.test.app.di.modules.AppModule;
import com.github.stephanenicolas.toothpick.smoothie.BuildConfig;

import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import toothpick.Toothpick;
import toothpick.configuration.Configuration;

public class QuwaApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        configureToothpick();
        RxJavaPlugins.setErrorHandler(Throwable::printStackTrace);
    }

    private void configureToothpick() {
        if (BuildConfig.DEBUG) {
            Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes());

        } else {
            Toothpick.setConfiguration(Configuration.forProduction());
        }

        ToothpickUtils.getAppScope().installModules(new AppModule(this));
    }
}
