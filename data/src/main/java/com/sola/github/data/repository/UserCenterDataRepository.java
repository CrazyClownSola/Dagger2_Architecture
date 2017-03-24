package com.sola.github.data.repository;

import com.sola.github.data.entity.net.FireBaseUserInfoEntity;
import com.sola.github.data.net.AApiConnection;
import com.sola.github.data.net.FireBaseAuthService;
import com.sola.github.data.scope.HttpsRestAdapter;
import com.sola.github.domain.params.params.uc.UserInfoDTO;
import com.sola.github.domain.repository.repository.UserCenterRepository;

import java.util.Collection;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by Sola
 * 2017/2/20.
 */
public class UserCenterDataRepository extends AConnectionRepository implements UserCenterRepository {

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
    UserCenterDataRepository(
            @HttpsRestAdapter AApiConnection apiConnection) {
        super(apiConnection);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public Observable<UserInfoDTO> requestUserInfo(String userId) {
        // 网络请求
        return apiConnection.createService(
                FireBaseAuthService.BASE_URL, FireBaseAuthService.class)
                .requestDaggerUserInfo("user_dagger.json")
                .flatMap(this::defaultErrorMapper)
                .flatMap(fireBaseUserInfoEntity -> Observable.just(transform(fireBaseUserInfoEntity)));
    }

    @Override
    public Observable<Collection<UserInfoDTO>> requestUserList() {
        return null;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private UserInfoDTO transform(FireBaseUserInfoEntity entity) {
        UserInfoDTO retDto = new UserInfoDTO();
        retDto.setId(entity.getId());
        retDto.setName(entity.getName());
        retDto.setAge(entity.getAge());
        retDto.setMobile(entity.getMobile());
        retDto.setGender(entity.getGender());
        retDto.setUserId(entity.getUserId());
        return retDto;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
