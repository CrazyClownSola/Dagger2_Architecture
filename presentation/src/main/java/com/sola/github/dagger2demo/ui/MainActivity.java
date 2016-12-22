package com.sola.github.dagger2demo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.di.activity.MainActivityComponent;
import com.sola.github.dagger2demo.di.base.HasSubComponentBuilders;
import com.sola.github.dagger2demo.enums.ESubType;
import com.sola.github.dagger2demo.ui.presenter.MainPresenter;
import com.sola.github.dagger2demo.utils.DensityUtil;
import com.sola.github.dagger2demo.utils.LinearDecoration;
import com.sola.github.tools.adapter.RecyclerClickBaseAdapter;
import com.sola.github.tools.delegate.IRecyclerViewClickDelegate;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

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

    @BindView(R.id.id_app_bar_layout)
    AppBarLayout id_app_bar_layout;

    RecyclerClickBaseAdapter<IRecyclerViewClickDelegate> adapter;

    private boolean isTouched;

    private float cacheY;

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
        initListener();
        id_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        id_recycler_view.setItemAnimator(new DefaultItemAnimator());
        id_recycler_view.addItemDecoration(new LinearDecoration(
                getContext(),
                LinearDecoration.VERTICAL_LIST).setMargins(
                DensityUtil.dip2px(getContext(), 50), 0,
                DensityUtil.dip2px(getContext(), 10), 0));

        adapter = new RecyclerClickBaseAdapter<>(getContext(), null);
        id_recycler_view.setAdapter(adapter);
        requestData();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    @OnClick(R.id.id_btn_fab)
    public void onClick() {
        requestData();
    }

    private void requestData() {
        mainPresenter.requestMainListData(1, 20, iRecyclerViewClickDelegate ->
                adapter.refreshList(iRecyclerViewClickDelegate));
    }

    private void initListener() {
        id_recycler_view.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!isTouched) {
                        cacheY = event.getRawY();
                        isTouched = true;
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    isTouched = false;
                    if (event.getRawY() > cacheY) {
                        if (adapter.getItemCount() == 0) {
                            id_app_bar_layout.setExpanded(true);
                        } else {
                            int index = ((LinearLayoutManager)
                                    id_recycler_view.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
                            id_app_bar_layout.setExpanded(index == 0);
                        }
                    } else if (event.getRawY() < cacheY) {
                        id_app_bar_layout.setExpanded(false);
                    }
                    break;
            }
            return false;
        });
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
    // Inner and Anonymous Classes
    // ===========================================================

}
