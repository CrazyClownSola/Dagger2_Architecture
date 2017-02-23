package com.sola.github.dagger2demo.di.subs;

import com.sola.github.dagger2demo.di.base.SubComponentBuilder;
import com.sola.github.dagger2demo.di.scope.CJScope;
import com.sola.github.dagger2demo.ui.cj_demo.ACJBaseActivity;
import com.sola.github.domain.cases.AUserCenterCase;
import com.sola.github.domain.cases.impl.UserCenterCaseImpl;
import com.sola.github.tools.utils.LogUtils;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by zhangluji
 * 2017/2/17.
 * todo 复合向跳转的组件，主要测试方向，对应一个需求点，需要跳转多个Activity的组件化，特点这些Activity 公用同一个Case逻辑系列
 */
@CJScope
@Subcomponent(
        modules = CompoundJumpActivityComponent.CompoundJumpModule.class
)
public interface CompoundJumpActivityComponent {
    // ===========================================================
    // Constants
    // ===========================================================

    String TAG = "Sola/CJComponent";

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Subcomponent.Builder
    interface Builder extends SubComponentBuilder<CompoundJumpModule, CompoundJumpActivityComponent> {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    @Module
    class CompoundJumpModule {

        public CompoundJumpModule() {
            LogUtils.i(TAG, "CompoundJumpModule() called");
        }

        @Provides
        //@Singleton // 这个标注其实是一个非常值得深思的一个点，这代表实例的生命周期跟随那一部分
        // 这里到底是跟随整个Component还是跟随组件中的一个Activity，这就是一个问题所在
        @CJScope
            // 有待测试
        AUserCenterCase provideUserCenterCase(UserCenterCaseImpl impl) {
            return impl;
        }

    }

//    AUserCenterCase userCenterCase();

    /**
     * 这里考量了很久，最终决定用这种显式注入的方式进行，通过这种方式将一些通用的实例放在泛类当中
     *
     * @param activity 这里应该用一个通用的基类Activity
     */
    void inject(ACJBaseActivity activity);

}
