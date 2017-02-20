package com.sola.github.domain.cases;

import com.sola.github.domain.exception.ErrorDTO;
import com.sola.github.domain.exception.ErrorDelegate;
import com.sola.github.domain.executor.NetExecutorThread;
import com.sola.github.domain.executor.UIExecutorThread;
import com.sola.github.domain.params.params.uc.UserInfoDTO;

import rx.functions.Action1;

/**
 * Created by zhangluji
 * 2017/2/20.
 */
public abstract class AUserCenterCase extends ComplexConnectionCase {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    protected AUserCenterCase(
            NetExecutorThread threadExecutor,
            UIExecutorThread postExecutionThread,
            ErrorDelegate errorPresenter) {
        super(threadExecutor, postExecutionThread, errorPresenter);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    public abstract void requestUserInfo(
            String userId,
            Action1<UserInfoDTO> onNext,
            Action1<ErrorDTO> onError
    );

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
