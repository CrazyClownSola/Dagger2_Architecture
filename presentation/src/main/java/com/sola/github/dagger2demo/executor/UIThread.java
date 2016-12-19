package com.sola.github.dagger2demo.executor;


import com.sola.github.domain.UIExecutorThread;

import javax.inject.Inject;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by slove
 * 2016/12/19.
 */
public class UIThread implements UIExecutorThread {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    UIThread() {
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
