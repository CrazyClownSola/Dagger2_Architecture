package com.sola.github.dagger2demo.ui.presenter;

import com.sola.github.dagger2demo.di.scope.ActivityScope;
import com.sola.github.dagger2demo.ui.params.BBSDataViewDTO;
import com.sola.github.domain.interactor.ABBSCase;
import com.sola.github.params.BBSDataDTO;
import com.sola.github.tools.delegate.IRecyclerViewClickDelegate;

import java.util.Collection;
import java.util.LinkedList;

import javax.inject.Inject;

import rx.functions.Action1;

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

    public void requestMainListData(
            int pageCount, int pageSize,
            Action1<Collection<IRecyclerViewClickDelegate>> onNext) {
        abbsCase.searchBBSList(
                pageCount, pageSize,
                bbsDataDTOs -> onNext.call(transform(bbsDataDTOs)),
                errorDTO -> {

                });
    }


    // ===========================================================
    // Methods
    // ===========================================================

    private Collection<IRecyclerViewClickDelegate> transform(Collection<BBSDataDTO> bbsDataDTOs) {
        Collection<IRecyclerViewClickDelegate> retList = new LinkedList<>();
        if (bbsDataDTOs != null) {
            for (BBSDataDTO dto :
                    bbsDataDTOs) {
                retList.add(new BBSDataViewDTO(dto));
            }
        }
        return retList;
    }
    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
