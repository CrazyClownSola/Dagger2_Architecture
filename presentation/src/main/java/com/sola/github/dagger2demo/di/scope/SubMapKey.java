package com.sola.github.dagger2demo.di.scope;

import dagger.MapKey;

/**
 * Created by slove
 * 2016/12/14.
 */
@MapKey(unwrapValue = false)
public @interface SubMapKey {

    int type();

    int index();

}
