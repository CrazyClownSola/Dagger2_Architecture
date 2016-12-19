package com.sola.github.data.repository;

import android.accounts.NetworkErrorException;

import com.sola.github.data.entity.BaseResultEntity;
import com.sola.github.data.net.ApiConnection;

import rx.Observable;

/**
 * Created by zhangluji
 * 2016/12/19.
 */
@SuppressWarnings("unused")
abstract class AConnectionRepository {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    final ApiConnection apiConnection;

    // ===========================================================
    // Constructors
    // ===========================================================

    AConnectionRepository(ApiConnection apiConnection) {
        this.apiConnection = apiConnection;
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

    <T extends BaseResultEntity> Observable<T> defaultErrorMapper(T dto) {
        if (dto == null) {
            return Observable.error(new NullPointerException());
        } else if (!dto.isSuccess()) {
            return Observable.error(new NetworkErrorException(dto.getMessage()));
        } else
            return Observable.just(dto);
    }


    <T extends BaseResultEntity, R> Observable<R> defaultErrorMapper(T dto, R ret) {
        if (dto == null) {
            return Observable.error(new NullPointerException());
        } else if (!dto.isSuccess()) {
            return Observable.error(new NetworkErrorException(dto.getMessage()));
        } else
            return Observable.just(ret);
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
