package br.com.bradesco.daggerteste.main;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by felipearaujo on 06/12/16.
 */
@Module
public class MainActivityModule {

    private final String mBaseUrl;

    public MainActivityModule(String mBaseUrl) {
        this.mBaseUrl = mBaseUrl;
    }

    @Provides
    @MainActivityScope
    public Retrofit provideRetrofit(Retrofit.Builder builder) {
        return builder.baseUrl(mBaseUrl)
                .build();
    }

}
