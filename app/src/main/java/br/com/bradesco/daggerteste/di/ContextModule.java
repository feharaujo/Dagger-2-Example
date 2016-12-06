package br.com.bradesco.daggerteste.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by felipearaujo on 05/12/16.
 */
@Module
public class ContextModule {

    private final Context mContext;

    public ContextModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    public Context providesContext() {
        return mContext;
    }
}
