package com.sola.github.dagger2demo.di.base;

import android.content.Context;

import com.sola.github.dagger2demo.enums.ESubType;

/**
 * Created by slove
 * 2016/12/14.
 * <p>
 * 判断当前模块是否含有子组建的接口
 */
public interface HasSubComponentBuilders {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    SubComponentBuilder getSubComponentBuild(ESubType type, int index);

    // ===========================================================
    // Methods
    // ===========================================================

}
