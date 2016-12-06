package br.com.bradesco.daggerteste;

import android.app.Application;

import br.com.bradesco.daggerteste.di.ApplicationComponent;
import br.com.bradesco.daggerteste.di.ContextModule;
import br.com.bradesco.daggerteste.di.DaggerApplicationComponent;

/**
 * Created by felipearaujo on 06/12/16.
 */

public class AppApplication extends Application {

    private ApplicationComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerApplicationComponent.builder()
                .contextModule(new ContextModule(getApplicationContext()))
                .build();
    }

    public ApplicationComponent getAppComponent() {
        return appComponent;
    }
}
