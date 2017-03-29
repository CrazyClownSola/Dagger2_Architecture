package com.sola.github.dagger2demo.ui;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.os.Bundle;
import android.support.annotation.LayoutRes;

/**
 * Created by Sola
 * 2017/3/29.
 */

public class LoaderActivity extends RxBindingBaseActivity {

    @Override
    protected void injectBinding(@LayoutRes int resId) {

    }

    @Override
    protected void initExtras(Intent intent) {

    }

    @Override
    protected void doAfterView() {
        getLoaderManager().initLoader(0, null, new LoaderManager.LoaderCallbacks<Object>() {
            @Override
            public Loader<Object> onCreateLoader(int id, Bundle args) {
                return null;
            }

            @Override
            public void onLoadFinished(Loader<Object> loader, Object data) {

            }

            @Override
            public void onLoaderReset(Loader<Object> loader) {

            }
        });
    }

}
