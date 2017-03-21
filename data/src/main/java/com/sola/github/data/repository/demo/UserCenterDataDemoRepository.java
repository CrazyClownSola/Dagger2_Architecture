package com.sola.github.data.repository.demo;

import com.sola.github.domain.params.params.uc.UserInfoDTO;
import com.sola.github.domain.repository.repository.UserCenterRepository;
import com.sola.github.tools.utils.TypeBuilder;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Sola
 * 2017/2/20.
 */
public class UserCenterDataDemoRepository implements UserCenterRepository {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    UserCenterDataDemoRepository() {
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public Observable<UserInfoDTO> requestUserInfo(String userId) {
        return Observable.just(buildUserData()); // 测试用，这里直接返回测试build数据
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private UserInfoDTO buildUserData() {
        UserInfoDTO retDto = new UserInfoDTO();
        retDto.setId(TypeBuilder.getInstance().generateRanId());
        retDto.setName("测试人名");
        retDto.setAge(TypeBuilder.getInstance().generateRanId(100));
        retDto.setMobile("1111111111");
        retDto.setGender("N");
        retDto.setUserId("000x");
        return retDto;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
