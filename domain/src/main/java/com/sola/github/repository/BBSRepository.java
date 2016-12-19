package com.sola.github.repository;

import com.sola.github.params.BBSDataDTO;

import java.util.Collection;

import rx.Observable;

/**
 * Created by zhangluji
 * 2016/12/19.
 */
public interface BBSRepository {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    Observable<Collection<BBSDataDTO>> requestBBSList(int pageCount, int pageSize);

}
