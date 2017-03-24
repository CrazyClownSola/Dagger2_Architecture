package com.sola.github.domain.repository.repository;

import com.sola.github.domain.params.params.uc.UserInfoDTO;

import java.util.Collection;

import rx.Observable;

/**
 * Created by Sola
 * 2017/2/20.
 */
public interface UserCenterRepository {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    Observable<UserInfoDTO> requestUserInfo(String userId);

    Observable<Collection<UserInfoDTO>> requestUserList();
}
