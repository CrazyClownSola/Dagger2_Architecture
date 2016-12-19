package com.sola.github.data.net;

import com.sola.github.data.entity.net.ABaseResponseListEntity;
import com.sola.github.data.entity.net.BBSDataEntity;
import com.sola.github.data.utils.RestConfig;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by zhangluji
 * 2016/12/19.
 */
public interface BBSService {
    // ===========================================================
    // Constants
    // ===========================================================

    String BASE_URL = RestConfig.BASE_URL + "/2";

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================
    @GET("statuses/public_timeline")
    Observable<ABaseResponseListEntity<BBSDataEntity>> requestBBSList(
            @Query("access_token") String token,
            @Query("count") int count,
            @Query("page") int page);
}
