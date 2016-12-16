package com.sola.github.dagger2demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.di.activity.MainActivityComponent;
import com.sola.github.dagger2demo.di.base.HasSubComponentBuilders;
import com.sola.github.dagger2demo.enums.ESubType;
import com.sola.github.dagger2demo.ui.presenter.MainPresenter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by slove
 * 2016/12/16.
 */
public class MainActivity extends RxBaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @Inject
    MainPresenter mainPresenter;

    @BindView(R.id.id_recycler_view)
    RecyclerView id_recycler_view;

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
    }

    @Override
    protected void doAfterView() {
        initComponent();

        id_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        id_recycler_view.setItemAnimator(new DefaultItemAnimator());

//        mainPresenter
    }

    private void initComponent() {
        if (getApplication() instanceof HasSubComponentBuilders) {
            ((MainActivityComponent.Builder) ((HasSubComponentBuilders) getApplication())
                    .getSubComponentBuild(ESubType.TYPE_ACTIVITY, 2))
                    .build()
                    .inject(this);
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
