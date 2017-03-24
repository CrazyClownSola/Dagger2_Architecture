package com.sola.github.dagger2demo.ui.cj_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.databinding.ActivityCjMainBinding;
import com.sola.github.dagger2demo.di.app.AppComponent;
import com.sola.github.dagger2demo.di.base.HasComponent;
import com.sola.github.dagger2demo.navigator.Navigator;
import com.sola.github.tools.adapter.RecyclerBaseAdapter;
import com.sola.github.tools.delegate.IRecyclerViewDelegate;

/**
 * Created by Sola
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

    RecyclerView id_recycler_view;

    RecyclerBaseAdapter<IRecyclerViewDelegate> adapter;

    ActivityCjMainBinding binding;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    @SuppressWarnings("unchecked")
    private Navigator getNavigator() {
        return ((HasComponent<AppComponent>) getApplication()).getComponent().provideNavigator();
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cj_main);
    }

    @Override
    protected void initExtras(Intent intent) {
        binding.setToolTitle(title);
        binding.setListener(v -> doRequest());
    }

    @Override
    protected void doAfterView() {
        getToastUtils().makeToast(this, "我还在测试", Toast.LENGTH_SHORT);
        if (adapter == null)
            adapter = new RecyclerBaseAdapter<>(getContext(), null);
        adapter.setListener((v, viewDto) ->
                getNavigator().switchActivity(
                        this,
                        CJSecondActivity.class));
        id_recycler_view = (RecyclerView) findViewById(R.id.id_recycler_view);
        id_recycler_view.setItemAnimator(new DefaultItemAnimator()); // 默认的一些加载动效可能很多人无法接受
        id_recycler_view.setAdapter(adapter);
        doRequest();
    }

    @Override
    protected void injectBinding(@LayoutRes int resId) {
        binding = buildBinding(resId);
//        binding = DataBindingUtil.setContentView(this, resId);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void doRequest() {
        getPresenter().requestUserList(
                iRecyclerViewDelegates -> adapter.refreshList(iRecyclerViewDelegates),
                errorDTO -> getToastUtils().makeToast(this, "Error [" + errorDTO.getErrorMessage() + "]", Toast.LENGTH_SHORT));
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
