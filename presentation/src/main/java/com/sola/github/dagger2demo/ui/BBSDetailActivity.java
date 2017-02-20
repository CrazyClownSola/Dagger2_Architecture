package com.sola.github.dagger2demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.ui.params.BaseViewDTO;
import com.sola.github.dagger2demo.utils.LinearDecoration;
import com.sola.github.domain.params.params.bbs.BBSDataDTO;
import com.sola.github.tools.adapter.RecyclerBaseAdapter;

import butterknife.BindView;

/**
 * Created by zhangluji
 * 2016/12/23.
 */
@SuppressWarnings("unused")
public class BBSDetailActivity extends RxBaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private String title;

    private BBSDataDTO data;

    @BindView(R.id.id_recycler_view)
    RecyclerView id_recycler_view;

    RecyclerBaseAdapter<BaseViewDTO> adapter;

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
        setContentView(R.layout.activity_bbs_detail);
    }

    @Override
    protected void doAfterView() {
        initRecyclerView();
    }

    @Override
    protected void initExtras(Intent intent) {
        Bundle extras_ = intent.getExtras();
        if (extras_ != null) {
            if (extras_.containsKey("data"))
                data = (BBSDataDTO) extras_.getSerializable("data");
            if (extras_.containsKey("title"))
                title = extras_.getString("title");
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    private void initRecyclerView() {
        id_recycler_view.setLayoutManager(new LinearLayoutManager(getContext()));
        id_recycler_view.setItemAnimator(new DefaultItemAnimator());
        id_recycler_view.addItemDecoration(
                new LinearDecoration(getContext(), LinearDecoration.VERTICAL_LIST)
                        .setMargins(0, 0, 0, 0));

        adapter = new RecyclerBaseAdapter<>(getContext(), null);
        adapter.setListener((v, viewDto) -> {

        });
        id_recycler_view.setAdapter(adapter);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
