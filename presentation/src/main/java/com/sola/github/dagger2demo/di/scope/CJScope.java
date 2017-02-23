package com.sola.github.dagger2demo.di.scope;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by slove
 * 2016/12/14.
 * 对于不同生命周期定义不同的Scope
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface CJScope {

}
