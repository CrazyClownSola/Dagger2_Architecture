package com.sola.github.dagger2demo.di.app;

import android.app.Application;
import android.content.Context;

import com.sola.github.dagger2demo.executor.NetExecutor;
import com.sola.github.dagger2demo.executor.UIThread;
import com.sola.github.dagger2demo.utils.Utils;
import com.sola.github.data.exception.ErrorDelegateImpl;
import com.sola.github.data.net.ApiConnection;
import com.sola.github.data.repository.BBSDataRepository;
import com.sola.github.domain.ErrorDelegate;
import com.sola.github.domain.NetExecutorThread;
import com.sola.github.domain.UIExecutorThread;
import com.sola.github.domain.interactor.ABBSCase;
import com.sola.github.domain.interactor.impl.BBSCaseImpl;
import com.sola.github.repository.BBSRepository;

import java.lang.ref.WeakReference;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by slove
 * 2016/12/14.
 * 标记接口：指定Application生命周期中所持有的实例
 */
@Module
public class AppModule {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final WeakReference<Application> mApplication;

    // ===========================================================
    // Constructors
    // ===========================================================

    public AppModule(Application mApplication) {
        this.mApplication = new WeakReference<>(mApplication);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // 工具类映射
    // ===========================================================

    @Provides
    @Singleton
    Context provideApplication() {
        return mApplication.get();
    }

    @Provides
    @Singleton
    NetExecutorThread provideNetExecutorThread(NetExecutor executor) {
        return executor;
    }

    @Provides
    @Singleton
    UIExecutorThread provideUIExecutorThread(UIThread thread) {
        return thread;
    }

    @Provides
    @Singleton
    ErrorDelegate provideErrorDelegate(ErrorDelegateImpl impl) {
        return impl;
    }

    // ===========================================================
    // Case集合的映射类
    // ===========================================================

    @Provides
    @Singleton
    ABBSCase provideABBSCase(BBSCaseImpl impl) {
        return impl;
    }

    @Provides
    @Singleton
    BBSRepository provideBBSRepository(BBSDataRepository repository) {
        return repository;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
