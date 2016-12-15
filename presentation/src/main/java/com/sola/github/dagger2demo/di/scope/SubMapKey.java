package com.sola.github.dagger2demo.di.scope;

import com.sola.github.dagger2demo.enums.ESubType;

import java.lang.annotation.Documented;

import dagger.MapKey;

/**
 * Created by slove
 * 2016/12/14.
 */
@MapKey(unwrapValue = false)
public @interface SubMapKey {

    ESubType type();

    int index();

}
