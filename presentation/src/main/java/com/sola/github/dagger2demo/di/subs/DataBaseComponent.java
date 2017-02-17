package com.sola.github.dagger2demo.di.subs;

import android.util.Log;

import com.sola.github.dagger2demo.di.base.SubComponentBuilder;

import dagger.Module;
import dagger.Subcomponent;

/**
 * Created by slove
 * 2016/12/14.
 * 数据库相关模块 -- 这个数据库模块单独分出来，一方面是为了尝试新的调用方式，二是希望代码能符合逻辑进行分割
 * 组建有两个概念，组建当中存有的实例，分为私有实例和公有实例
 * 私有实例的访问方式需要调用放Inject自己进入当前的组建中
 * 公有实例，需要将该实例以接口的形式开放出来，然后通过实例化该组建，然后获取
 */
@Subcomponent(
        modules = DataBaseComponent.DataBaseModule.class
)
public interface DataBaseComponent {
    // ===========================================================
    // Constants
    // ===========================================================

    String TAG = "Sola";

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Subcomponent.Builder
    interface Builder extends SubComponentBuilder<DataBaseModule, DataBaseComponent> {
    }

    // ===========================================================
    // Methods
    // ===========================================================

    @Module
    class DataBaseModule {

        public DataBaseModule() {
            Log.d(TAG, "DataBaseModule() called");
        }
    }

    void testConnect();

}
