package br.com.bradesco.daggerteste.main;

import br.com.bradesco.daggerteste.details.DetailsActivity;
import br.com.bradesco.daggerteste.di.ApplicationComponent;
import dagger.Component;

/**
 * Created by felipearaujo on 06/12/16.
 */
@MainActivityScope
@Component(modules = MainActivityModule.class, dependencies = ApplicationComponent.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(DetailsActivity detailsActivity);

}
