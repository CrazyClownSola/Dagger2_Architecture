package com.sola.github.dagger2demo.ui.params;

import android.content.Context;
import android.support.annotation.StringRes;

import com.sola.github.tools.delegate.IRecyclerViewDelegate;
import com.sola.github.tools.utils.TypeBuilder;

/**
 * Created by Sola
 * 2016/12/20.
 */
@SuppressWarnings("unused")
public abstract class BaseViewDTO<T> implements IRecyclerViewDelegate {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    protected T data;

    // ===========================================================
    // Constructors
    // ===========================================================

    BaseViewDTO(T data) {
        this.data = data;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public int getViewType(int position) {
        return TypeBuilder.getInstance().generateId();
    }

    // ===========================================================
    // Methods
    // ===========================================================

    String getResourceStr(Context context, @StringRes int resId, Object... param) {
        return context.getString(resId, param);
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
