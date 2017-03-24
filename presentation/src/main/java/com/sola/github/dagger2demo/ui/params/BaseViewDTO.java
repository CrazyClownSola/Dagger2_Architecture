package com.sola.github.dagger2demo.ui.params;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sola.github.dagger2demo.BR;
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

    @Override
    @SuppressWarnings("unchecked")
    public void refreshView(Context context, RecyclerView.ViewHolder holder, int position) {
        if (data == null)
            return;
        BaseHolder viewHolder = (BaseHolder) holder;
        viewHolder.setData(data);
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

    class BaseHolder extends RecyclerView.ViewHolder {

        ViewDataBinding binding;

        BaseHolder(View itemView) {
            super(itemView);
            this.binding = DataBindingUtil.bind(itemView);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        public void setData(Object obj) {
            binding.setVariable(BR.data, obj);
            // 这段代码很重要，有点强制刷新的意思，如果这段代码不加，binding会被推出到下一帧，这样会是的界面无限刷新
            // 建议配置到ViewHolder里面去
            binding.executePendingBindings();
        }
    }

}
