package br.com.bradesco.daggerteste.di;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by felipearaujo on 05/12/16.
 */
@Module(includes = NetworkModule.class)
public class ClientModule {

    @ApplicationScope
    @Provides
    public Retrofit.Builder providesRetrofitBuilder(OkHttpClient okHttpClient, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory);
    }

/*    @ApplicationScope
    @Named("rxRetrofit")
    @Provides
    public Retrofit.Builder providesRxRetrofitBuilder(OkHttpClient okHttpClient,
                                                      GsonConverterFactory gsonConverterFactory,
                                                      RxJavaCallAdapterFactory rxJavaCallAdapterFactory) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory);
    }*/

    @ApplicationScope
    @Provides
    public GsonConverterFactory providesGsonConverterFactory() {
        return GsonConverterFactory.create();
    }

    @ApplicationScope
    @Provides
    public RxJavaCallAdapterFactory providesRxJavaCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }


}
