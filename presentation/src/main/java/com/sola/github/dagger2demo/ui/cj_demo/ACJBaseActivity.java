package com.sola.github.dagger2demo.ui.cj_demo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sola.github.dagger2demo.di.base.HasSubComponentBuilders;
import com.sola.github.dagger2demo.di.subs.CompoundJumpActivityComponent;
import com.sola.github.dagger2demo.enums.ESubType;
import com.sola.github.dagger2demo.navigator.Navigator;
import com.sola.github.dagger2demo.presenter.CJPresenter;
import com.sola.github.dagger2demo.ui.RxBindingBaseActivity;
import com.sola.github.dagger2demo.utils.ToastUtils;

import javax.inject.Inject;

/**
 * Created by Sola
 * 2017/2/22.
 */
public abstract class ACJBaseActivity extends RxBindingBaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @Inject
    CJPresenter presenter;

    @Inject
    Navigator navigator;

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

    public Navigator getNavigator() {
        return navigator;
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
