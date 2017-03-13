package com.sola.github.tools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.sola.github.tools.delegate.IRecyclerViewClickDelegate;

import java.util.Collection;

/**
 * Created by Sola
 * 2016/12/20.
 */
public class RecyclerComplexClickBaseAdapter<
        Param extends IRecyclerViewClickDelegate,
        Header extends IRecyclerViewClickDelegate,
        Footer extends IRecyclerViewClickDelegate>
        extends RecyclerComplexBaseAdapter<Param, Header, Footer> {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    public RecyclerComplexClickBaseAdapter(Context mContext, Collection<Param> list) {
        super(mContext, list);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

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
