package com.sola.github.dagger2demo.di.subs;

import com.sola.github.dagger2demo.di.base.SubComponentBuilder;
import com.sola.github.dagger2demo.di.scope.CJScope;
import com.sola.github.tools.utils.LogUtils;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by Sola
 * 2017/2/17.
 * todo 带有Fragment的子组件
 */
@CJScope
@Subcomponent(
        modules = FragmentActivityComponent.FragmentModule.class
)
public interface FragmentActivityComponent {
    // ===========================================================
    // Constants
    // ===========================================================

    String TAG = "Sola/FragComponent";

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Subcomponent.Builder
    interface Builder extends SubComponentBuilder<FragmentModule, FragmentActivityComponent> {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    @Module
    class FragmentModule {

        public FragmentModule() {
            LogUtils.d(TAG, "FragmentModule() called");
        }

    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
