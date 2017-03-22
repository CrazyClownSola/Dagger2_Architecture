package com.sola.github.dagger2demo.ui.fb_demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.ui.RxBaseActivity;
import com.sola.github.dagger2demo.ui.RxBindingBaseActivity;

/**
 * Created by Sola
 * 2017/2/28.
 */
public class FragmentOneActivity extends RxBindingBaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

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
        setContentView(R.layout.activity_fragment_one);
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
