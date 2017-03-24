package com.sola.github.data.repository.demo;

import com.sola.github.domain.params.params.uc.UserInfoDTO;
import com.sola.github.domain.repository.repository.UserCenterRepository;
import com.sola.github.tools.utils.TypeBuilder;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Random;

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
        return Observable.just(buildRandomUser(0)); // 测试用，这里直接返回测试build数据
    }

    @Override
    public Observable<Collection<UserInfoDTO>> requestUserList() {
        return Observable.just(buildUserList()); // 测试用，这里直接返回测试build数据
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private UserInfoDTO buildRandomUser(int index) {
        UserInfoDTO retDto = new UserInfoDTO();
        retDto.setId(TypeBuilder.getInstance().generateRanId());
        retDto.setName("No." + index);
        retDto.setAge(TypeBuilder.getInstance().generateRanId(100));
        retDto.setMobile("1111111111");
        retDto.setGender(index % 2 == 0 ? "N" : "F");
        retDto.setUserId("No." + retDto.getId());
        retDto.setPic("https://qn-store.qbcdn.com/40d83f4e110e298761417b9b98d33175e26cf6db-4609-45e6-b30e-021bdaebdd15");
        return retDto;
    }

    private Collection<UserInfoDTO> buildUserList() {
        Random random = new Random();
        Collection<UserInfoDTO> retList = new LinkedList<>();
        for (int i = 0; i < random.nextInt(100); i++) {
            retList.add(buildRandomUser(i));
        }
        return retList;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
