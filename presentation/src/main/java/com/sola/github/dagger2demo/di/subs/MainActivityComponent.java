package com.sola.github.dagger2demo.di.subs;

import com.sola.github.dagger2demo.di.base.ActivityComponent;
import com.sola.github.dagger2demo.di.base.SubComponentBuilder;
import com.sola.github.dagger2demo.di.scope.ActivityScope;
import com.sola.github.dagger2demo.ui.MainActivity;
import com.sola.github.tools.utils.LogUtils;

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
            LogUtils.i(TAG, "MainActivityModule() called");
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    void inject(MainActivity activity);

}
