package com.sola.github.dagger2demo.di.base;

import com.sola.github.dagger2demo.di.scope.SubMapKey;
import com.sola.github.dagger2demo.di.subs.DataBaseComponent;
import com.sola.github.dagger2demo.di.subs.TestActivityComponent;
import com.sola.github.dagger2demo.enums.ESubType;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created by slove
 * 2016/12/14.
 */
@Module(
        subcomponents = {
                TestActivityComponent.class,
                DataBaseComponent.class
        }
)
public abstract class SubComponentBindingModule {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @IntoMap // 将这个实例绑定到 MainApplication所持有的实例Map中去
//    @Provides
    @Binds
    @SubMapKey(type = ESubType.TYPE_DB, index = 1)
    // 其实暂且没想到更好的 区分方式 用Enum?
    abstract SubComponentBuilder dataBaseComponentBuilder(DataBaseComponent.Builder impl);

    @IntoMap // 将这个实例绑定到 MainApplication所持有的实例Map中去
//    @Provides
    @Binds
    @SubMapKey(type = ESubType.TYPE_ACTIVITY, index = 1)
    // 其实暂且没想到更好的 区分方式 用Enum?
    abstract SubComponentBuilder mainActivityComponentBuilder(TestActivityComponent.Builder impl);

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
