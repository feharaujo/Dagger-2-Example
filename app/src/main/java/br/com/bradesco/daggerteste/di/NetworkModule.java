package br.com.bradesco.daggerteste.di;

import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by felipearaujo on 05/12/16.
 */
@Module(includes = ContextModule.class)
public class NetworkModule {

    private static final String CACHE_CONTROL = "Cache-Control";

    @ApplicationScope
    @Provides
    public OkHttpClient providesOkHttpClient(
            Cache cache,
            @Named("offlineInterceptor") Interceptor offlineInterceptor,
            @Named("onlineInterceptor") Interceptor onlineInterceptor) {

        return new OkHttpClient.Builder()
                .addInterceptor(offlineInterceptor)
                .addNetworkInterceptor(onlineInterceptor)
                .cache(cache)
                .build();
    }

    @ApplicationScope
    @Provides
    public Cache provideCache(File cacheFile, @Named("cacheSize") byte valueCache) {
        return new Cache(cacheFile, valueCache * 1024 * 1024);
    }

    @ApplicationScope
    @Named("cacheSize")
    @Provides
    public byte providesCacheSize() {
        // 10 MB
        return 10;
    }

    @ApplicationScope
    @Provides
    public File providesCacheFile(Context context) {
        return new File(context.getCacheDir(), "http-cache");
    }

    @ApplicationScope
    @Named("onlineInterceptor")
    @Provides
    public Interceptor providesCacheInterceptor(@Named("onlineCacheControl") final CacheControl cacheControl) {
        return chain -> {
            Response response = chain.proceed(chain.request());
            return response.newBuilder()
                    .header(CACHE_CONTROL, cacheControl.toString())
                    .build();
        };
    }

    @ApplicationScope
    @Named("offlineInterceptor")
    @Provides
    public Interceptor providesOfflineCacheInterceptor(
            final Context context,
            @Named("offlineCacheControl") final CacheControl cacheControl) {

        return chain -> {
            Request request = chain.request();

            //if (!Connectivity.isConnected(context)) {
            request = request.newBuilder()
                    .cacheControl(cacheControl)
                    .build();
            //  }

            return chain.proceed(request);
        };
    }

    @ApplicationScope
    @Named("onlineCacheControl")
    @Provides
    public CacheControl providesOnlineCacheControl() {
        return new CacheControl.Builder()
                .maxAge(1, TimeUnit.DAYS)
                .build();
    }

    @ApplicationScope
    @Named("offlineCacheControl")
    @Provides
    public CacheControl providesOfflineCacheControl() {
        return new CacheControl.Builder()
                .maxAge(7, TimeUnit.DAYS)
                .build();
    }

}
