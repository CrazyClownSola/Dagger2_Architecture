package com.sola.github.dagger2demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.di.base.HasSubComponentBuilders;
import com.sola.github.dagger2demo.di.subs.DataBaseComponent;
import com.sola.github.dagger2demo.enums.ESubType;

/**
 * Created by slove
 * 2016/12/14.
 */
public class MainActivity extends RxBaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponent();
    }

    @Override
    protected void doAfterView() {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    private void initComponent() {
        if (getApplication() instanceof HasSubComponentBuilders) {
            ((DataBaseComponent.Builder)
                    ((HasSubComponentBuilders) getApplication())
                            .getSubComponentBuild(ESubType.TYPE_DB, 0))
                    .build();
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
