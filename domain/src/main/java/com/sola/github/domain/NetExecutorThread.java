package com.sola.github.domain;

import java.util.concurrent.Executor;

import rx.Scheduler;

/**
 * Created by slove
 * 2016/12/19.
 */
public interface NetExecutorThread extends Executor {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    Scheduler getScheduler();

}
