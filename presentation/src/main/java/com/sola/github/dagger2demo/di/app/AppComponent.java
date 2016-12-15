package com.sola.github.dagger2demo.di.app;

import com.sola.github.dagger2demo.MainApplication;
import com.sola.github.dagger2demo.di.base.SubComponentBindingModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by slove
 * 2016/12/14.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                SubComponentBindingModule.class
        }
)
public interface AppComponent {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    void inject(MainApplication application);
}
