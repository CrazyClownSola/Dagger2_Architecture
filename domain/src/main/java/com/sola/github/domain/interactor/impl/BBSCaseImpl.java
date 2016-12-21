package com.sola.github.domain.interactor.impl;

import com.sola.github.domain.ErrorDTO;
import com.sola.github.domain.ErrorDelegate;
import com.sola.github.domain.NetExecutorThread;
import com.sola.github.domain.UIExecutorThread;
import com.sola.github.domain.interactor.ABBSCase;
import com.sola.github.params.BBSDataDTO;
import com.sola.github.params.BBSPostsDTO;
import com.sola.github.repository.BBSRepository;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import rx.functions.Action1;

/**
 * Created by slove
 * 2016/12/19.
 */
public class BBSCaseImpl extends ABBSCase {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final BBSRepository repository;

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    BBSCaseImpl(
            NetExecutorThread threadExecutor,
            UIExecutorThread uiExecutor,
            ErrorDelegate errorDelegate,
            BBSRepository repository
    ) {
        super(threadExecutor, uiExecutor, errorDelegate);
        this.repository = repository;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void searchBBSList(
            int pageCount, int pageSize,
            Action1<Collection<BBSDataDTO>> onNext, Action1<ErrorDTO> onError) {
        execute(repository.requestBBSList(1, pageCount, pageSize), onNext, getErrorAction(onError));
    }

    @Override
    public void getPostsReplyList(
            int postsId, Action1<List<BBSPostsDTO>> onNext, Action1<ErrorDTO> onError) {
        // 这里写法有很多种，可以任君挑选
        // 同时，可以在这里进行一次数据处理
        execute(repository.requestBBSPostsList(postsId)
                        .flatMapIterable(bbsPostsDTOs -> bbsPostsDTOs)
                        .toSortedList((bbsPostsDTO, bbsPostsDTO2) -> { // 这里是一种处理方式，这个排序也可以放在Repository当中做，两者并没有太多区别
                            return bbsPostsDTO.getTimeStamp() > bbsPostsDTO2.getTimeStamp() ? -1 :
                                    bbsPostsDTO.getTimeStamp() == bbsPostsDTO2.getTimeStamp() ? 0 : 1;
                        })
                , onNext, getErrorAction(onError));
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
