package com.sola.github.dagger2demo.di.subs;

import android.util.Log;

import com.sola.github.dagger2demo.di.base.ActivityComponent;
import com.sola.github.dagger2demo.di.base.SubComponentBuilder;
import com.sola.github.dagger2demo.di.scope.ActivityScope;
import com.sola.github.dagger2demo.ui.MainActivity;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by slove
 * 2016/12/16.
 */
@ActivityScope
@Subcomponent(
        modules = MainActivityComponent.MainActivityModule.class
)
public interface MainActivityComponent extends ActivityComponent<MainActivity> {

    // ===========================================================
    // Constants
    // ===========================================================

    String TAG = "Sola";

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Subcomponent.Builder
    interface Builder extends SubComponentBuilder<MainActivityModule, MainActivityComponent> {

    }

    @Module
    class MainActivityModule {

        public MainActivityModule() {
            Log.d(TAG, "MainActivityModule() called");
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    void inject(MainActivity activity);

}
