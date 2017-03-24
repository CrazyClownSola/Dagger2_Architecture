package com.sola.github.dagger2demo.di.view;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by Sola
 * 2017/3/24.
 */

public interface IBindingAdapter {

    @BindingAdapter(value = {"srcUrl", "error"}, requireAll = false)
    void setImageUrl(ImageView view, String url, Drawable error);
}
