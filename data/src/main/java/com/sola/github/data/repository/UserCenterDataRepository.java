package com.sola.github.data.repository;

import com.sola.github.domain.params.params.uc.UserInfoDTO;
import com.sola.github.domain.repository.repository.UserCenterRepository;

import rx.Observable;

/**
 * Created by zhangluji
 * 2017/2/20.
 */
public class UserCenterDataRepository implements UserCenterRepository {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public Observable<UserInfoDTO> requestUserInfo(String userId) {
        // 网络请求
        return null;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
