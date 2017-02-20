package com.sola.github.domain.repository.repository;

import com.sola.github.domain.params.params.uc.UserInfoDTO;

import rx.Observable;

/**
 * Created by zhangluji
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

}
