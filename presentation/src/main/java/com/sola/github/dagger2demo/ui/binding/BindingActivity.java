package com.sola.github.dagger2demo.ui.binding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.databinding.ActivityBindingMainBinding;

/**
 * Created by Sola
 * 2017/3/16.
 */
public class BindingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityBindingMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_binding_main);
//        binding.setMain("Im binding");
    }

}
