package com.sola.github.dagger2demo.di.view.adapter;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;

import com.sola.github.dagger2demo.di.view.IBindingAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by Sola
 * 2017/3/24.
 */
public class DefaultBindingAdapter implements IBindingAdapter {

    @Override
    public void setImageUrl(ImageView view, String url, Drawable error) {
        Log.e("Sola", "BindingAdapter() called with: view = [" + view + "], url = [" + url + "]");
        if (!TextUtils.isEmpty(url)) {
            RequestCreator creator = Picasso.with(view.getContext()).load(url);
            if (error != null)
                creator.error(error);
            if (creator != null)
                creator.into(view);
        }
    }

//    @BindingAdapter(value = {"srcUrl", "error"}, requireAll = false)
//    public void setImageUrl(ImageView view, String url, Drawable error) {
//        Log.e("Sola", "setImageUrl() called with: view = [" + view + "], url = [" + url + "]");
//        if (!TextUtils.isEmpty(url)) {
//            RequestCreator creator = Picasso.with(view.getContext()).load(url);
//            if (error != null)
//                creator.error(error);
//            if (creator != null)
//                creator.into(view);
//        }
//    }

}
