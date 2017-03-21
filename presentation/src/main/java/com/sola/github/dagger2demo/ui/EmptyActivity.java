package com.sola.github.dagger2demo.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.databinding.ActivityEmptyBinding;
import com.sola.github.dagger2demo.di.app.AppComponent;
import com.sola.github.dagger2demo.di.base.HasComponent;
import com.sola.github.dagger2demo.navigator.BundleFactory;
import com.sola.github.dagger2demo.navigator.Navigator;
import com.sola.github.dagger2demo.ui.cj_demo.CJMainActivity;

/**
 * Created by Sola
 * 2017/2/23.
 */
public class EmptyActivity extends RxBindingBaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    ActivityEmptyBinding binding;

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

    @SuppressWarnings("unchecked")
    private BundleFactory getBundleFactory() {
        return ((HasComponent<AppComponent>) getApplication()).getComponent().provideBundleFactory();
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_empty);
        binding.setListener(this::btnClick);
    }

    @Override
    protected void initExtras(Intent intent) {
        binding.setToolTitle(title);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    public void btnClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn_cj_main:
                getNavigator().switchActivity(
                        this,
                        CJMainActivity.class,
                        getBundleFactory()
                                .putString("title", "CJ_Main")
                                .build()
                );
                break;
            case R.id.id_btn_main:
                getNavigator().switchActivity(
                        this,
                        MainActivity.class,
                        getBundleFactory()
                                .putString("title", "Main")
                                .build()
                );
                break;
            case R.id.id_btn_test:
                break;

            case R.id.id_btn_binding:
                break;
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
