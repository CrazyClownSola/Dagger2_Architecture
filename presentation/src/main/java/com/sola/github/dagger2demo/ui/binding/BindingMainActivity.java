package com.sola.github.dagger2demo.ui.binding;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.sola.github.dagger2demo.R;

/**
 * Created by Sola
 * 2017/3/16.
 */

public class BindingMainActivity extends AppCompatActivity {

//    LayoutDefaultHeaderToolBarBinding toolBarBinding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binding_main);
//        toolBarBinding = DataBindingUtil.inflate(
//                getLayoutInflater(),
//                R.layout.layout_default_header_tool_bar, null, false);
//        toolBarBinding.setTool_title("test");

    }
}
