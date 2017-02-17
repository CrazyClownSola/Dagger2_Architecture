package com.sola.github.dagger2demo.ui.presenter;

import com.sola.github.dagger2demo.di.scope.ActivityScope;
import com.sola.github.dagger2demo.ui.params.BBSDataViewDTO;
import com.sola.github.dagger2demo.ui.params.BaseViewDTO;
import com.sola.github.domain.ErrorDTO;
import com.sola.github.domain.interactor.ABBSCase;
import com.sola.github.params.BBSDataDTO;

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
            Action1<Collection<BaseViewDTO<BBSDataDTO>>> onNext,
            Action1<ErrorDTO> onError) {
        abbsCase.searchBBSList(
                pageCount, pageSize,
                bbsDataDTOs -> onNext.call(transform(bbsDataDTOs)),
                onError);
    }


    // ===========================================================
    // Methods
    // ===========================================================

    private Collection<BaseViewDTO<BBSDataDTO>> transform(Collection<BBSDataDTO> bbsDataDTOs) {
        Collection<BaseViewDTO<BBSDataDTO>> retList = new LinkedList<>();
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
