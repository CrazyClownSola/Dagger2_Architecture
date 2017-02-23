package com.sola.github.dagger2demo.ui.cj_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sola.github.dagger2demo.di.base.HasSubComponentBuilders;
import com.sola.github.dagger2demo.di.subs.CompoundJumpActivityComponent;
import com.sola.github.dagger2demo.enums.ESubType;
import com.sola.github.dagger2demo.presenter.CJPresenter;
import com.sola.github.dagger2demo.ui.RxBaseActivity;
import com.sola.github.dagger2demo.utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by zhangluji
 * 2017/2/22.
 */
public abstract class ACJBaseActivity extends RxBaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @Inject
    CJPresenter presenter;

    @Inject
    ToastUtils toastUtils;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    protected CompoundJumpActivityComponent getCJComponent() {
        return ((CompoundJumpActivityComponent.Builder)
                ((HasSubComponentBuilders) getApplication())
                        .getSubComponentBuild(ESubType.TYPE_ACTIVITY, 3))
                .build();
    }

    public CJPresenter getPresenter() {
        return presenter;
    }

    public ToastUtils getToastUtils() {
        return toastUtils;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getCJComponent().inject(this);
    }


    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
