package com.sola.github.dagger2demo;

import android.app.Application;
import android.content.Context;

import com.sola.github.dagger2demo.di.app.AppComponent;
import com.sola.github.dagger2demo.di.app.DaggerAppComponent;
import com.sola.github.dagger2demo.di.base.HasSubComponentBuilders;
import com.sola.github.dagger2demo.di.base.SubComponentBuilder;
import com.sola.github.dagger2demo.di.scope.SubMapKey;
import com.sola.github.dagger2demo.di.scope.SubMapKeyCreator;
import com.sola.github.dagger2demo.di.subs.DataBaseComponent;
import com.sola.github.dagger2demo.enums.ESubType;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by slove
 * 2016/12/14.
 */
public class MainApplication extends Application implements HasSubComponentBuilders {

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

    public AppComponent getAppComponent() {
        return appComponent;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder().build();
        appComponent.inject(this);
    }

    @Override
    public SubComponentBuilder getSubComponentBuild(ESubType type, int index) {
        SubComponentBuilder builder = subComponentBuilderMap.get(SubMapKeyCreator.createSubMapKey(type, 1));
        // todo 这里有待考证
        if (type == ESubType.TYPE_DB && builder instanceof DataBaseComponent.Builder) {
            ((DataBaseComponent.Builder) builder)
                    .moduleBuild(new DataBaseComponent.DataBaseModule());
        }
        return builder;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}