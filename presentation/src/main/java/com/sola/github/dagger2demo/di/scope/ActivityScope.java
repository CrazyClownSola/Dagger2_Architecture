package com.sola.github.dagger2demo.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by slove
 * 2016/12/14.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityScope {

}
