package com.sola.github.dagger2demo.ui.fb_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.databinding.ActivityFragmentContentBinding;
import com.sola.github.dagger2demo.ui.RxBindingBaseActivity;

/**
 * Created by Sola
 * 2017/2/28.
 */
public class FragmentContentActivity extends RxBindingBaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    ActivityFragmentContentBinding binding;

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
        setContentView(R.layout.activity_fragment_content);
    }

    @Override
    protected void injectBinding(@LayoutRes int resId) {
        binding = buildBinding(resId);
    }

    @Override
    protected void initExtras(Intent intent) {

    }

    @Override
    protected void doAfterView() {

    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
