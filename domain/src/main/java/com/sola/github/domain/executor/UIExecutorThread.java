package com.sola.github.domain.executor;

import java.util.concurrent.Executor;

import rx.Scheduler;

/**
 * Created by zhangluji
 * 2017/2/20.
 */
public interface UIExecutorThread {
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
