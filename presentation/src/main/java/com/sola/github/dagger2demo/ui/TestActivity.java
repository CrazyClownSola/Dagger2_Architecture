package com.sola.github.dagger2demo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.di.base.HasSubComponentBuilders;
import com.sola.github.dagger2demo.di.subs.TestActivityComponent;
import com.sola.github.dagger2demo.enums.ESubType;
import com.sola.github.dagger2demo.ui.presenter.TestPresenter;
import com.sola.github.dagger2demo.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by slove
 * 2016/12/14.
 */
public class TestActivity extends RxBaseActivity {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

//    @Inject
//    TestPresenter presenter;

    @Inject
    ActivityUtils utils;

    @BindView(R.id.id_text_title)
    TextView id_text_title;

    @BindView(R.id.id_text_second)
    TextView id_text_second;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

//    private DataBaseComponent getDataComponent() {
//        if (getApplication() instanceof HasSubComponentBuilders) {
//            return ((DataBaseComponent.Builder)
//                    ((HasSubComponentBuilders) getApplication())
//                            .getSubComponentBuild(ESubType.TYPE_DB, 1))
//                    .build();
//        }
//        return null;
//    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    @Override
    protected void doAfterView() {
        if (getApplication() instanceof HasSubComponentBuilders) {
            // 这里采用显示调用
            TestPresenter presenter = ((TestActivityComponent.Builder)
                    ((HasSubComponentBuilders) getApplication())
                            .getSubComponentBuild(ESubType.TYPE_ACTIVITY, 1))
                    .moduleBuild(new TestActivityComponent.TestActivityModule(this))
                    .build()
                    .getMainPresenter();

            String text = presenter.utilInit();
            id_text_title.setText(text);
            text = presenter.subInit();
            id_text_second.setText(text);
        }
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
