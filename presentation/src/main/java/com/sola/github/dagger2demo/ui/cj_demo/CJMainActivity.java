package com.sola.github.dagger2demo.ui.cj_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sola.github.dagger2demo.R;
import com.sola.github.tools.adapter.RecyclerBaseAdapter;
import com.sola.github.tools.delegate.IRecyclerViewDelegate;

import butterknife.BindView;

/**
 * Created by zhangluji
 * 2017/2/22.
 * 用于测试CompoundJump 跳转的
 */
public class CJMainActivity extends ACJBaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @BindView(R.id.id_recycler_view)
    RecyclerView id_recycler_view;

    RecyclerBaseAdapter<IRecyclerViewDelegate> adapter;

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
        setContentView(R.layout.activity_cj_main);
    }

    @Override
    protected void doAfterView() {
        getToastUtils().makeToast(this, "我还在测试", Toast.LENGTH_SHORT);
        if (adapter == null)
            adapter = new RecyclerBaseAdapter<>(getContext(), null);
        id_recycler_view.setItemAnimator(new DefaultItemAnimator()); // 默认的一些加载动效可能很多人无法接受
        id_recycler_view.setAdapter(adapter);
        getPresenter().requestUserInfo("",
                iRecyclerViewDelegate -> adapter.addItem(iRecyclerViewDelegate),
                errorDTO -> getToastUtils().makeToast(this, "测试数据", Toast.LENGTH_SHORT));
    }

    @Override
    protected void initExtras(Intent intent) {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
