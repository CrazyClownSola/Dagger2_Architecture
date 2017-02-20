package com.sola.github.dagger2demo.di.subs;

import com.sola.github.dagger2demo.di.base.SubComponentBuilder;
import com.sola.github.domain.cases.AUserCenterCase;
import com.sola.github.domain.cases.impl.UserCenterCaseImpl;

import dagger.Module;
import dagger.Provides;
import dagger.Subcomponent;

/**
 * Created by zhangluji
 * 2017/2/17.
 * todo 复合向跳转的组件，主要测试方向，对应一个需求点，需要跳转多个Activity的组件化，特点这些Activity 公用同一个Case逻辑系列
 */
@Subcomponent(
        modules = CompoundJumpActivityComponent.CompoundJumpModule.class
)
public interface CompoundJumpActivityComponent {
    // ===========================================================
    // Constants
    // ===========================================================

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

        }

        @Provides
        AUserCenterCase provideUserCenterCase(UserCenterCaseImpl impl) {
            return impl;
        }

    }

    AUserCenterCase userCenterCase();

}
