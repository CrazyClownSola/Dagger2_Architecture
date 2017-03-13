package com.sola.github.data.net;

import com.sola.github.data.entity.net.FireBaseUserInfoEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Sola
 * 2017/3/13.
 */
public interface FireBaseAuthService {

    String BASE_URL = "https://solatest-d36e7.firebaseio.com/";

    @GET("/users/{userId}")
    Observable<FireBaseUserInfoEntity> requestDaggerUserInfo(@Path("userId") String userId);

}
