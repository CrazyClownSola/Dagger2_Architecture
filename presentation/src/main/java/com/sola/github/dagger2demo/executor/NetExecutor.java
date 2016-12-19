package com.sola.github.dagger2demo.executor;

import android.support.annotation.NonNull;


import com.sola.github.domain.NetExecutorThread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * author: Sola
 * 2015/10/28
 */
public class NetExecutor implements NetExecutorThread {

    // ===========================================================
    // Constants
    // ===========================================================

    // 池中保存的线程数，包括空闲线程
    private static final int INITIAL_POOL_SIZE = 10;
    // 线程池允许的最大线程数
    private static final int MAX_POOL_SIZE = 20;
    // 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
    private static final int KEEP_ALIVE_TIME = 15;
    // 参数的时间单位
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    // ===========================================================
    // Fields
    // ===========================================================

    // 线程池实例
    private final ThreadPoolExecutor threadPoolExecutor;

    // ===========================================================
    // Constructors

    @Inject
    NetExecutor() {
        BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>();
        ThreadFactory threadFactory = new JobThreadFactory();
        threadPoolExecutor = new ThreadPoolExecutor(
                INITIAL_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT,
                workQueue,
                threadFactory
        );
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void execute(@NonNull Runnable command) {
        threadPoolExecutor.execute(command);
    }

    @Override
    public Scheduler getScheduler() {
        return Schedulers.from(this);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    private class JobThreadFactory implements ThreadFactory {

        @Override
        public Thread newThread(@NonNull Runnable r) {
            return new Thread(r, "Android_");
        }
    }

}
