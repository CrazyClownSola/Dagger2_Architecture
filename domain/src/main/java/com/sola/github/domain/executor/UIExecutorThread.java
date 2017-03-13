package com.sola.github.domain.executor;

import rx.Scheduler;

/**
 * Created by Sola
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
