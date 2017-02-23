package com.sola.github.dagger2demo.presenter;

import com.sola.github.dagger2demo.ui.params.UserInfoViewDTO;
import com.sola.github.domain.cases.AUserCenterCase;
import com.sola.github.domain.exception.ErrorDTO;
import com.sola.github.tools.delegate.IRecyclerViewDelegate;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by zhangluji
 * 2017/2/22.
 */
public class CJPresenter {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final AUserCenterCase userCenterCase;

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    CJPresenter(
            AUserCenterCase userCenterCase) {
        this.userCenterCase = userCenterCase;
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

    public void requestUserInfo(
            String userId,
            Action1<IRecyclerViewDelegate> onNext,
            Action1<ErrorDTO> onError) {
        userCenterCase.requestUserInfo(userId,
                userInfoDTO -> onNext.call(new UserInfoViewDTO(userInfoDTO)),
                onError);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
