package com.sola.github.dagger2demo.presenter;

import com.sola.github.dagger2demo.ui.params.UserInfoViewDTO;
import com.sola.github.domain.cases.AUserCenterCase;
import com.sola.github.domain.exception.ErrorDTO;
import com.sola.github.domain.params.params.uc.UserInfoDTO;
import com.sola.github.tools.delegate.IRecyclerViewDelegate;

import java.util.Collection;
import java.util.LinkedList;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by Sola
 * 2017/2/22.
 */
@SuppressWarnings("unused")
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

    public void requestUserList(
            Action1<Collection<IRecyclerViewDelegate>> onNext,
            Action1<ErrorDTO> onError) {
        userCenterCase.requestUserList(userInfoDTOs -> onNext.call(transform(userInfoDTOs)), onError);
    }

    private Collection<IRecyclerViewDelegate> transform(Collection<UserInfoDTO> userInfoDTOs) {
        Collection<IRecyclerViewDelegate> retList = new LinkedList<>();
        for (UserInfoDTO user :
                userInfoDTOs) {
            retList.add(new UserInfoViewDTO(user));
        }
        return retList;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
