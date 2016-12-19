package com.sola.github.dagger2demo.ui.presenter;

import com.sola.github.dagger2demo.di.scope.ActivityScope;
import com.sola.github.domain.interactor.ABBSCase;

import javax.inject.Inject;

/**
 * Created by slove
 * 2016/12/16.
 */
@ActivityScope
public class MainPresenter implements IPresenter {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final ABBSCase abbsCase;

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    MainPresenter(ABBSCase abbsCase) {
        this.abbsCase = abbsCase;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    public void requestMainListData(int pageCount, int pageSize) {
        abbsCase.searchBBSList(
                pageCount, pageSize,
                bbsDataDTOs -> {

                }, errorDTO -> {

                });
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
