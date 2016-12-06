package br.com.bradesco.daggerteste.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by felipearaujo on 06/12/16.
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface ApplicationScope {
}
