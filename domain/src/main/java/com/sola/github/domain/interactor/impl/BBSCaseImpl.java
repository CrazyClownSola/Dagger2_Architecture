package com.sola.github.domain.interactor.impl;

import com.sola.github.domain.ErrorDTO;
import com.sola.github.domain.ErrorDelegate;
import com.sola.github.domain.NetExecutorThread;
import com.sola.github.domain.UIExecutorThread;
import com.sola.github.domain.interactor.ABBSCase;
import com.sola.github.params.BBSDataDTO;
import com.sola.github.repository.BBSRepository;

import java.util.Collection;

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

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
