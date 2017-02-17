package com.sola.github.dagger2demo.navigator;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.di.app.DaggerAppComponent;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by zhangluji
 * 2016/12/23.
 *
 * @Singleton 这个标注的作用是，这个类在对应的Component当中是以单例的方式存在
 * 具体代码可以参考{@link DaggerAppComponent},观察代码会发现一个类
 * {@link dagger.internal.DoubleCheck}, 这是确保Component中获取到Navigator的实例为单例的根据
 * 有一点要注意的是，@Singleton 这个标注只针对在Component当中的类才有效
 */
@Singleton
@SuppressWarnings("unused")
public class Navigator {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    Navigator() {
        Log.d("Sola", "Navigator: constructor");
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

    public void switchActivity(final Context context, Class<?> cls) {
        switchActivity(context, cls, null);
    }

    public void switchActivity(final Context context, Class<?> cls, Bundle bundle) {
        switchActivityForResult(context, -1, cls, bundle);
    }

    public void switchActivityForResult(final Context context, int requestCode, Class<?> cls) {
        switchActivityForResult(context, requestCode, cls, null);
    }

    public void switchActivity(final Context context, Intent intent) {
        switchActivityForResult(context, -1, intent);
    }

    private void switchActivityForResult(final Context context, int requestCode, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null && !bundle.isEmpty())
            intent.putExtras(bundle);
        switchActivityForResult(context, requestCode, intent);
    }

    private void switchActivityForResult(final Context context, int requestCode, Intent intent) {
        switchActivityForResult(context, requestCode, intent, R.anim.fade_in_left, R.anim.activity_back);
    }

    private void switchActivityForResult(final Context context, int requestCode, Intent intent, int targetInAnim, int currentOutAnim) {
        if (context instanceof Activity && requestCode != -1)
            ((Activity) context).startActivityForResult(intent, requestCode);
        else
            context.startActivity(intent);
        if (context instanceof Activity)
            // 第一个参数是目标Activity进入时的动画，第二个参数是当前Activity退出时的动画
            ((Activity) context).overridePendingTransition(targetInAnim, currentOutAnim);
    }

    @SuppressWarnings("unused")
    public void switchActivityForResult(final Context context, int requestCode, Class<?> cls, Bundle bundle, int targetInAnim, int currentOutAnim) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (bundle != null && !bundle.isEmpty())
            intent.putExtras(bundle);
        if (context instanceof Activity && requestCode != -1) {
            ((Activity) context).startActivityForResult(intent, requestCode);
        } else {
            context.startActivity(intent);
        }
        if (context instanceof Activity)
            // 第一个参数是目标Activity进入时的动画，第二个参数是当前Activity退出时的动画
            ((Activity) context).overridePendingTransition(targetInAnim, currentOutAnim);
    }

    public void switchActivityWithTransition(
            final Context context, Class<?> cls, Bundle bundle, int requestCode,
            int targetInAnim, int currentOutAnim,
            View shareView, String shareTarget
    ) {
        if (shareView == null)
            switchActivityWithTransition(context, cls, bundle, requestCode, targetInAnim, currentOutAnim);
        else
            switchActivityWithTransition(context, cls, bundle, requestCode, targetInAnim, currentOutAnim,
                    Pair.create(shareView, shareTarget));
    }

    @SafeVarargs
    private final void switchActivityWithTransition(
            final Context context, Class<?> cls, Bundle bundle, int requestCode,
            int targetInAnim, int currentOutAnim,
            Pair<View, String>... pairs
    ) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && context instanceof Activity) {
            if (pairs != null && pairs.length > 0) {
                ActivityOptions options =
                        ActivityOptions.makeSceneTransitionAnimation(((Activity) context), pairs);
                if (bundle != null && !bundle.isEmpty())
                    options.toBundle().putAll(bundle);
                if (requestCode == -1)
                    context.startActivity(intent, options.toBundle());
                else
                    ((Activity) context).startActivityForResult(intent, requestCode, options.toBundle());
            } else {
                if (bundle != null && !bundle.isEmpty())
                    intent.putExtras(bundle);
                if (requestCode == -1)
                    context.startActivity(intent, bundle);
                else
                    ((Activity) context).startActivityForResult(intent, requestCode, bundle);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            context.startActivity(intent, bundle);
        } else {
            context.startActivity(intent);
        }
        if (context instanceof Activity)
            ((Activity) context).overridePendingTransition(targetInAnim, currentOutAnim);
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
