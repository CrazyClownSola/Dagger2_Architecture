package com.sola.github.dagger2demo.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Sola
 * 2017/2/22.
 * 提供了一种{@link Qualifier}的使用方式，可以带参数
 */
@Qualifier
@SuppressWarnings("unused")
@Retention(RetentionPolicy.RUNTIME)
public @interface GitHubRestAdapter {

    String name();

    int type();
}
