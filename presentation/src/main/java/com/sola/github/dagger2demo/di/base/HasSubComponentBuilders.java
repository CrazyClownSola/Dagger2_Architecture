package com.sola.github.dagger2demo.di.base;

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

    /**
     * @param type  不同类型的模块组
     * @param index 序列值，暂且没啥用
     * @return 返回对应的子模块组
     */
    SubComponentBuilder getSubComponentBuild(int type, int index);

    // ===========================================================
    // Methods
    // ===========================================================

}
