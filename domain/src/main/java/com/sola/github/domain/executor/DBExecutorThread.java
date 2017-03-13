package com.sola.github.domain.executor;

import java.util.concurrent.Executor;

import rx.Scheduler;

/**
 * Created by Sola
 * 2017/2/20.
 * 数据库线程队列
 */
@SuppressWarnings("unused")
public interface DBExecutorThread extends Executor {
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
