package br.com.bradesco.daggerteste.di;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by felipearaujo on 05/12/16.
 */
@Module(includes = NetworkModule.class)
public class PicassoModule {

    @ApplicationScope
    @Provides
    public Picasso providesPicasso(Context context, OkHttp3Downloader downloader) {
        return new Picasso.Builder(context)
                .downloader(downloader)
                .build();
    }

    @ApplicationScope
    @Provides
    public OkHttp3Downloader providesDownloader(OkHttpClient okHttpClient){
        return new OkHttp3Downloader(okHttpClient);
    }

}
