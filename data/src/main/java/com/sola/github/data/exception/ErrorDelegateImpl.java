package com.sola.github.data.exception;


import com.sola.github.domain.exception.ErrorDTO;
import com.sola.github.domain.exception.ErrorDelegate;
import com.sola.github.tools.utils.LogUtils;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by slove
 * 2016/12/19.
 */
public class ErrorDelegateImpl implements ErrorDelegate {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final SimpleDateFormat dataFormat;

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    ErrorDelegateImpl() {
        dataFormat = new SimpleDateFormat("", Locale.getDefault());
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    @Override
    public Action1<Throwable> onError(Action1<ErrorDTO> func) {
        return throwable -> {
            throwable.printStackTrace();
            func.call(buildResult(throwable));
        };
    }

    @Override
    public Action1<Throwable> onError(Action0 func) {
        return throwable -> {
            throwable.printStackTrace();
            func.call();
        };
    }

    @Override
    public Action1<Throwable> onError() {
        return Throwable::printStackTrace;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    private ErrorDTO buildResult(Throwable error) {
        ErrorDTO retDto = new ErrorDTO();
        if (error instanceof IOException) {
            if (error instanceof SocketTimeoutException)
                retDto.setErrorMessage("网络不给力，请重试");
            else
                retDto.setErrorMessage("网络连接失败，刷新重试");
        } else {
            if (LogUtils.DEBUG)
                retDto.setErrorMessage(error.getMessage());
            else
                retDto.setErrorMessage("上述现象的发生都是程序猿的锅　╮（￣▽￣）╭ \n然并卵 ");
        }
        if (dataFormat != null)
            retDto.setTimestamp(dataFormat.format(new Date()));
        return retDto;
    }
}
