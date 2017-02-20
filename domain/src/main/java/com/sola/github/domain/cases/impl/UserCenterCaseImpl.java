package com.sola.github.domain.cases.impl;

import com.sola.github.domain.cases.AUserCenterCase;
import com.sola.github.domain.exception.ErrorDTO;
import com.sola.github.domain.exception.ErrorDelegate;
import com.sola.github.domain.executor.NetExecutorThread;
import com.sola.github.domain.executor.UIExecutorThread;
import com.sola.github.domain.params.params.uc.UserInfoDTO;
import com.sola.github.domain.repository.repository.UserCenterRepository;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by zhangluji
 * 2017/2/20.
 * Case的实现可以通过很多种方式去做
 */
public class UserCenterCaseImpl extends AUserCenterCase {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final UserCenterRepository userCenterRepository;

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    UserCenterCaseImpl(
            NetExecutorThread threadExecutor,
            UIExecutorThread postExecutionThread,
            ErrorDelegate errorPresenter,
            UserCenterRepository userCenterRepository) {
        super(threadExecutor, postExecutionThread, errorPresenter);
        this.userCenterRepository = userCenterRepository;

    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void requestUserInfo(String userId, Action1<UserInfoDTO> onNext, Action1<ErrorDTO> onError) {
        execute(userCenterRepository.requestUserInfo(userId), onNext, getErrorAction(onError));
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
