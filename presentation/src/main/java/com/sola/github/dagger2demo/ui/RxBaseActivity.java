package com.sola.github.dagger2demo.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.sola.github.dagger2demo.R;

import java.lang.ref.WeakReference;

import butterknife.ButterKnife;

/**
 * Created by slove
 * 2016/12/14.
 */
public abstract class RxBaseActivity extends AppCompatActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    protected final WeakReference<Context> mContext = new WeakReference<>(this);

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
    }

    @Override
    public void setIntent(Intent newIntent) {
        super.setIntent(newIntent);
        initExtras(newIntent);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        doAfterView();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        ButterKnife.bind(this);
        doAfterView();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
        doAfterView();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.fade_in_left, R.anim.activity_back);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0, R.anim.push_right_out);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    protected Context getContext() {
        return mContext.get();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    protected abstract void doAfterView();

    protected abstract void initExtras(Intent intent);

}
