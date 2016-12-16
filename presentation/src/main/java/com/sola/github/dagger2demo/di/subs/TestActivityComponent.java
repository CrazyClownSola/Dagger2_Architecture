package com.sola.github.dagger2demo.di.subs;

import com.sola.github.dagger2demo.di.base.ActivityComponent;
import com.sola.github.dagger2demo.di.base.ActivityModule;
import com.sola.github.dagger2demo.di.base.SubComponentBuilder;
import com.sola.github.dagger2demo.di.scope.ActivityScope;
import com.sola.github.dagger2demo.ui.TestActivity;
import com.sola.github.dagger2demo.ui.presenter.TestPresenter;
import com.sola.github.dagger2demo.utils.SubUtils;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by slove
 * 2016/12/16.
 * 这个组件的功能十分有针对性，直接针对MainActivity而存在，有自己的独立逻辑
 */
@ActivityScope
@Subcomponent(
        modules = TestActivityComponent.TestActivityModule.class
)
public interface TestActivityComponent extends ActivityComponent<TestActivity> {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Subcomponent.Builder
    interface Builder extends SubComponentBuilder<TestActivityModule, TestActivityComponent> {

    }

    @Module
    class TestActivityModule extends ActivityModule<TestActivity> {

        public TestActivityModule(TestActivity context) {
            super(context);
        }

        @Provides
        @ActivityScope
        SubUtils provideSubUtils() {
            return new SubUtils();
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    TestPresenter getMainPresenter();

}
