package com.sola.github.dagger2demo.utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.View;

/**
 * Created by 禄骥
 * 2016/8/1.
 */
@SuppressWarnings("unused")
public class CompatUtils {
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

    // ===========================================================
    // Methods
    // ===========================================================

    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String source) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(source,
                    Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(source);
        }
        return result;
    }

    public static Bitmap getResizedBitmap(final Bitmap bm, float newWidth, float newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = 1, scaleHeight = 1;
        if (newWidth != -1) {
            scaleWidth = newWidth / width;
            if (newHeight != -1) {
                scaleHeight = newHeight / height;
            } else {
                scaleHeight = newWidth / width;
            }
        } else {
            if (newHeight != -1) {
                scaleWidth = scaleHeight = newHeight / height;
            }
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static void setBackgroundCompat(final View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }


    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
