package com.sola.github.dagger2demo.ui.presenter;

import com.sola.github.dagger2demo.di.scope.ActivityScope;
import com.sola.github.dagger2demo.utils.PresenterUtils;
import com.sola.github.dagger2demo.utils.SubUtils;
import com.sola.github.dagger2demo.utils.Utils;

import javax.inject.Inject;

/**
 * Created by slove
 * 2016/12/16.
 */
@ActivityScope
public class TestPresenter implements IPresenter {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final Utils mUtils;
    private final SubUtils subUtils;

    @Inject
    PresenterUtils presenterUtils;

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    TestPresenter(Utils mUtils, SubUtils subUtils) {
        this.mUtils = mUtils;
        this.subUtils = subUtils;
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

    public String subInit() {
        return subUtils.init();
    }

    public String utilInit() {
        return mUtils.getTestString();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
