package com.sola.github.dagger2demo.ui.cj_demo;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.databinding.ActivityCjSecondBinding;
import com.sola.github.tools.adapter.RecyclerBaseAdapter;
import com.sola.github.tools.delegate.IRecyclerViewDelegate;

/**
 * Created by Sola
 * 2017/2/23.
 */
public class CJSecondActivity extends ACJBaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    RecyclerView id_recycler_view;

    RecyclerBaseAdapter<IRecyclerViewDelegate> adapter;
    ActivityCjSecondBinding binding;

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
        setContentView(R.layout.activity_cj_second);

    }

    @Override
    protected void initExtras(Intent intent) {

    }


    @Override
    protected void doAfterView() {
        if (adapter == null)
            adapter = new RecyclerBaseAdapter<>(getContext(), null);
//        adapter.setListener((v, viewDto) ->
//                getNavigator().switchActivity(
//                        this,
//                        CJSecondActivity.class));
        id_recycler_view = (RecyclerView) findViewById(R.id.id_recycler_view);
        id_recycler_view.setItemAnimator(new DefaultItemAnimator()); // 默认的一些加载动效可能很多人无法接受
        id_recycler_view.setAdapter(adapter);
        doRequest();
    }

    @Override
    protected void injectBinding(@LayoutRes int resId) {
//        binding = buildBinding(resId);
        binding = DataBindingUtil.bind(getLayoutInflater().
                inflate(resId, null));
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
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
