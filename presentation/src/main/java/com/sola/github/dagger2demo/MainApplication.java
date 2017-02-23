package com.sola.github.dagger2demo;

import android.app.Application;

import com.sola.github.dagger2demo.di.app.AppComponent;
import com.sola.github.dagger2demo.di.app.AppModule;
import com.sola.github.dagger2demo.di.app.DaggerAppComponent;
import com.sola.github.dagger2demo.di.base.HasComponent;
import com.sola.github.dagger2demo.di.base.HasSubComponentBuilders;
import com.sola.github.dagger2demo.di.base.SubComponentBuilder;
import com.sola.github.dagger2demo.di.scope.SubMapKey;
import com.sola.github.dagger2demo.di.scope.SubMapKeyCreator;
import com.sola.github.dagger2demo.di.subs.CompoundJumpActivityComponent;
import com.sola.github.dagger2demo.di.subs.DataBaseComponent;
import com.sola.github.dagger2demo.di.subs.MainActivityComponent;
import com.sola.github.dagger2demo.enums.ESubType;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by slove
 * 2016/12/14.
 */
public class MainApplication extends Application implements HasComponent<AppComponent>, HasSubComponentBuilders {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private AppComponent appComponent;

    @Inject
    Map<SubMapKey, SubComponentBuilder> subComponentBuilderMap;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().appModule(
                new AppModule(this)).build();
        appComponent.inject(this);
    }

    /**
     * @param type  不同类型的模块组
     * @param index 序列值，暂且没啥用
     * @return 理论上这里是不应该去初始化
     */
    @Override
    public SubComponentBuilder getSubComponentBuild(int type, int index) {
        SubComponentBuilder builder = subComponentBuilderMap.get(
                SubMapKeyCreator.createSubMapKey(type, index));
        // ? 一个疑问点是，这个Module的实例是否依旧是单例
        if (type == ESubType.TYPE_DB && builder instanceof DataBaseComponent.Builder) {
            ((DataBaseComponent.Builder) builder)
                    .moduleBuild(new DataBaseComponent.DataBaseModule());
        } else if (type == ESubType.TYPE_ACTIVITY) {
            if (index == 2 && builder instanceof MainActivityComponent.Builder) {
                ((MainActivityComponent.Builder) builder)
                        .moduleBuild(new MainActivityComponent.MainActivityModule());
            } else if (index == 3 && builder instanceof CompoundJumpActivityComponent.Builder) {
                ((CompoundJumpActivityComponent.Builder) builder).moduleBuild(
                        new CompoundJumpActivityComponent.CompoundJumpModule());
            }
        }
        return builder;
    }

    @Override
    public AppComponent getComponent() {
        if (appComponent == null)
            appComponent = DaggerAppComponent.builder().appModule(
                    new AppModule(this)).build();
        return appComponent;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
