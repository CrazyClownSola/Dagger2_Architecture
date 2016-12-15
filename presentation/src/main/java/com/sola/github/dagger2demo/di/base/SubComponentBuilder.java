package com.sola.github.dagger2demo.di.base;

/**
 * Created by slove
 * 2016/12/14.
 * 子组建构建器，用于提供给Application的子模块去获取到对应的组建Component
 */
public interface SubComponentBuilder<Module, Component> {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    SubComponentBuilder<Module, Component> moduleBuild(Module module);

    Component build();

    // ===========================================================
    // Methods
    // ===========================================================

}
