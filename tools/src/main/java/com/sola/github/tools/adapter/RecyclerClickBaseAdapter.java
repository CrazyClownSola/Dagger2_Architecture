package com.sola.github.tools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.sola.github.tools.delegate.IRecyclerViewClickDelegate;

import java.util.Collection;

/**
 * Created by zhangluji
 * 2016/12/20.
 */
public class RecyclerClickBaseAdapter<Param extends IRecyclerViewClickDelegate>
        extends RecyclerBaseAdapter<Param> {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    public RecyclerClickBaseAdapter(Context mContext, Collection<Param> list) {
        super(mContext, list);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    @Override
    public void setListener(OnRecyclerItemClickListener<Param> listener) {
        // 注意这里不去实现 listener 的赋值，意在控制itemListener在Param当中去实现，而不是提供公有出listener
        // 其实这几实现super方法也行
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Param delegate = getItemByPosition(position);
        if (delegate != null)
            holder.itemView.setOnClickListener(delegate::itemClick);
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
