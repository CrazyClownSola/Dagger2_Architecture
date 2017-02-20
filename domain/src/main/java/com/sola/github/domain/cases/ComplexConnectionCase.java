package com.sola.github.domain.cases;

import com.sola.github.domain.exception.ErrorDTO;
import com.sola.github.domain.exception.ErrorDelegate;
import com.sola.github.domain.executor.UIExecutorThread;

import java.util.concurrent.Executor;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Action2;
import rx.functions.Action3;
import rx.functions.Action4;
import rx.functions.Action5;
import rx.functions.Action6;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * author: Sola
 * 2016/1/8
 */
@SuppressWarnings("unused")
abstract class ComplexConnectionCase {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final Executor threadExecutor;

    private final UIExecutorThread uiExecutorThread;

    private final ErrorDelegate errorPresenter;

    private Subscription subscription = Subscriptions.empty();

    // ===========================================================
    // Constructors
    // ===========================================================

    ComplexConnectionCase(
            Executor threadExecutor,
            UIExecutorThread postExecutionThread,
            ErrorDelegate errorPresenter
    ) {
        this.threadExecutor = threadExecutor;
        this.uiExecutorThread = postExecutionThread;
        this.errorPresenter = errorPresenter;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    protected UIExecutorThread getUiExecutorThread() {
        return uiExecutorThread;
    }

    protected Executor getThreadExecutor() {
        return threadExecutor;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    protected Action1<Throwable> getErrorAction(Action1<ErrorDTO> onError) {
        return onError == null ? errorPresenter.onError() : errorPresenter.onError(onError);
    }

    /**
     * 提交请求
     *
     * @param onNext     正确流程处理
     * @param onError    出错处理
     * @param observable 处理流
     */
    protected <T> void execute(Observable<T> observable,
                               Action1<? super T> onNext,
                               Action1<Throwable> onError) {
        observable
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(onNext, onError == null ? errorPresenter.onError() : onError);
    }

    protected <T> Subscription executeRtn(Observable<T> observable,
                                          Action1<? super T> onNext,
                                          Action1<Throwable> onError) {
        return observable
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(onNext, onError == null ? errorPresenter.onError() : onError);
    }

    protected <T> void executeDefer(Func0<Observable<T>> function,
                                    Action1<? super T> onNext,
                                    Action1<Throwable> onError) {
//        this.subscription =
        Observable.defer(function)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(onNext, onError == null ? errorPresenter.onError() : onError);
    }


    /**
     * 这个方法是通过基类的方式创建Observable，这样可以让传入的参数更加轻松
     *
     * @param object  传入类型，
     * @param func    定义传入和传出参数之间关系的方法
     * @param onNext  响应回调
     * @param onError 失败响应回调
     * @param <T>     传参类型
     */
    protected <T, Result> void execute(Func1<T, Result> func, T object,
                                       Action1<? super Result> onNext,
                                       Action1<Throwable> onError) {
        Observable.create((Observable.OnSubscribe<Result>)
                subscriber -> {
                    try {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(func.call(object));
                            subscriber.onCompleted();
                        }
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }).subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(onNext, onError == null ? errorPresenter.onError() : onError);
    }


    protected <T, Result> void executeDefer(Func1<T, Observable<Result>> func, T object,
                                            Action1<? super Result> onNext,
                                            Action1<Throwable> onError) {
        func.call(object).subscribe(onNext,
                onError == null ? errorPresenter.onError() : onError);
    }

    /**
     * 当你不关心回调是什么的时候，可以使用这个方法
     *
     * @param func    在线程中需要运行的方法
     * @param object  参数
     * @param onError 错误回调
     * @param <T>     定义传入的参数类型
     */
    protected <T> void execute(Action1<T> func, T object,
                               Action1<Throwable> onError) {
        Observable.create((Observable.OnSubscribe<?>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    func.call(object);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(o -> {
                }, onError == null ? errorPresenter.onError() : onError);
    }

    protected void execute(Action0 func,
                           Action1<Throwable> onError) {
        Observable.create((Observable.OnSubscribe<?>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    func.call();
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(o -> {
                }, onError == null ? errorPresenter.onError() : onError);
    }


    protected <T1, T2, T3, T4> void execute(
            Action4<T1, T2, T3, T4> func,
            T1 param1, T2 param2, T3 param3, T4 param4,
            Action1<Throwable> onError) {
        Observable.create((Observable.OnSubscribe<?>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    func.call(param1, param2, param3, param4);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(o -> {
                }, onError == null ? errorPresenter.onError() : onError);
    }

    protected <T1, T2, T3, T4, T5, T6> void execute(
            Action6<T1, T2, T3, T4, T5, T6> func,
            T1 param1, T2 param2, T3 param3, T4 param4, T5 param5, T6 param6,
            Action1<Throwable> onError) {
        Observable.create((Observable.OnSubscribe<?>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    func.call(param1, param2, param3, param4, param5, param6);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(o -> {
                }, onError == null ? errorPresenter.onError() : onError);
    }

    protected <T1, T2, T3, T4, T5> void execute(
            Action5<T1, T2, T3, T4, T5> func,
            T1 param1, T2 param2, T3 param3, T4 param4, T5 param5,
            Action1<Throwable> onError) {
        Observable.create((Observable.OnSubscribe<?>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    func.call(param1, param2, param3, param4, param5);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(o -> {
                }, onError == null ? errorPresenter.onError() : onError);
    }


    protected <T1, T2, T3> void execute(
            Action3<T1, T2, T3> func, T1 param1, T2 param2, T3 param3,
            Action1<Throwable> onError) {
        Observable.create((Observable.OnSubscribe<?>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    func.call(param1, param2, param3);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(o -> {
                }, onError == null ? errorPresenter.onError() : onError);
    }

    protected <T1, T2> void execute(
            Action2<T1, T2> func, T1 param1, T2 param2,
            Action1<Throwable> onError) {
        Observable.create((Observable.OnSubscribe<?>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    func.call(param1, param2);
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(o -> {
                }, onError == null ? errorPresenter.onError() : onError);
    }

    /**
     * @param func     定义传入和传出参数之间关系的方法
     * @param onNext   响应回调
     * @param onError  失败响应回调
     * @param <Result> 返回类型
     */
    protected <Result> void execute(Func0<Result> func,
                                    Action1<? super Result> onNext,
                                    Action1<Throwable> onError) {
        Observable.create((Observable.OnSubscribe<Result>) subscriber -> {
            try {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(func.call());
                    subscriber.onCompleted();
                }
            } catch (Exception e) {
                subscriber.onError(e);
            }
        }).subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(uiExecutorThread.getScheduler())
                .subscribe(onNext, onError == null ? errorPresenter.onError() : onError);
    }


    /**
     * 关闭RxJava流程
     */
    public void unSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
