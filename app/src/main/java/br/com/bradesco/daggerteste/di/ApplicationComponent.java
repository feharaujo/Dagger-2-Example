package br.com.bradesco.daggerteste.di;

import com.squareup.picasso.Picasso;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by felipearaujo on 06/12/16.
 */
@ApplicationScope
@Component(modules = {ClientModule.class, PicassoModule.class})
public interface ApplicationComponent {

    Retrofit.Builder providesRetrofitBuilder();

    Picasso providesPicasso();

}
