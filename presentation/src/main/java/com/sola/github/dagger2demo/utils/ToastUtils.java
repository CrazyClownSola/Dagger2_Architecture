package com.sola.github.dagger2demo.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.sola.github.tools.utils.LogUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Sola
 * 2017/2/23.
 * 考量到会用到这个所以独立出来写
 * 后续记得补充一些控制toast 显示的方法
 */
@Singleton
@SuppressWarnings("unused")
public class ToastUtils {

    // ===========================================================
    // Constants
    // ===========================================================

    private final static String TAG = "Sola/ToastUtils";

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    ToastUtils() {
        LogUtils.i(TAG, "ToastUtils() called");
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public void makeToast(Context context, CharSequence text, int duration) {
        Toast.makeText(context, text, duration).show();
    }

    public void makeToast(Context context, @StringRes int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
