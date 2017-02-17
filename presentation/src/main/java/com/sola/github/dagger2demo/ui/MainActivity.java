package com.sola.github.dagger2demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.widget.Toast;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.di.activity.MainActivityComponent;
import com.sola.github.dagger2demo.di.base.HasSubComponentBuilders;
import com.sola.github.dagger2demo.enums.ESubType;
import com.sola.github.dagger2demo.navigator.BundleFactory;
import com.sola.github.dagger2demo.navigator.Navigator;
import com.sola.github.dagger2demo.ui.params.BaseViewDTO;
import com.sola.github.dagger2demo.ui.presenter.MainPresenter;
import com.sola.github.dagger2demo.utils.DensityUtil;
import com.sola.github.dagger2demo.utils.LinearDecoration;
import com.sola.github.params.BBSDataDTO;
import com.sola.github.tools.adapter.RecyclerBaseAdapter;

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

    @Inject
    Navigator navigator; // 这里只是单纯的调用

    @Inject
    BundleFactory bundleFactory;

    @BindView(R.id.id_recycler_view)
    RecyclerView id_recycler_view;

    @BindView(R.id.id_app_bar_layout)
    AppBarLayout id_app_bar_layout;

    RecyclerBaseAdapter<BaseViewDTO<BBSDataDTO>> adapter;

    private boolean isTouched;

    private float cacheY;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    private void initComponent() {
        if (getApplication() instanceof HasSubComponentBuilders) {
            ((MainActivityComponent.Builder) ((HasSubComponentBuilders) getApplication())
                    .getSubComponentBuild(ESubType.TYPE_ACTIVITY, 2))
                    .build()
                    .inject(this);
        }
    }

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

        adapter = new RecyclerBaseAdapter<>(getContext(), null);
        adapter.setListener((v, viewDto) ->
                navigator.switchActivity(getContext(),
                        BBSDetailActivity.class,
                        bundleFactory
                                .putString("title", "详情")
                                .putSerializable("data", viewDto.getData())
                                .build()));
        id_recycler_view.setAdapter(adapter);
        requestData();
    }

    @Override
    protected void initExtras(Intent intent) {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    @OnClick(R.id.id_btn_fab)
    public void onClick() {
        requestData();
    }

    private void requestData() {
        mainPresenter.requestMainListData(1, 20, viewDTOs ->
                        adapter.refreshList(viewDTOs),
                errorDTO -> Toast.makeText(
                        getContext(), errorDTO.getErrorMessage(), Toast.LENGTH_SHORT).show());
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

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
