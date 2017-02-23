package com.sola.github.dagger2demo.di.base;

import com.sola.github.dagger2demo.di.scope.ActivityScope;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by slove
 * 2016/12/14.
 */
@Module
public abstract class ActivityModule<T> {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    protected final WeakReference<T> context;

    // ===========================================================
    // Constructors
    // ===========================================================

    protected ActivityModule(T context) {
        this.context = new WeakReference<T>(context);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Provides
    T provideActivity() {
        return context.get();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
