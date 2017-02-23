package com.sola.github.dagger2demo.di.app;

import com.sola.github.dagger2demo.MainApplication;
import com.sola.github.dagger2demo.di.base.SubComponentBindingModule;
import com.sola.github.dagger2demo.navigator.BundleFactory;
import com.sola.github.dagger2demo.navigator.Navigator;

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


    /****
     * 注意，下面的接口是提供给需要调用这个组件当中实例，但是并不想注入该组件中的类进行调用
     ****/

    Navigator provideNavigator();

    BundleFactory provideBundleFactory();

}
