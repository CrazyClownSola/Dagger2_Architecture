package com.sola.github.domain.exception;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by slove
 * 2016/12/19.
 */
@SuppressWarnings("unused")
public interface ErrorDelegate {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    Action1<Throwable> onError();

    Action1<Throwable> onError(Action0 func);

    Action1<Throwable> onError(Action1<ErrorDTO> func);

}
