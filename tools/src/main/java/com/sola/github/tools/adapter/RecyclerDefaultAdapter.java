package com.sola.github.tools.adapter;

import android.content.Context;

import com.sola.github.tools.delegate.IRecyclerViewClickDelegate;

import java.util.Collection;

/**
 * Created by zhangluji
 * 2016/12/20.
 */
public class RecyclerDefaultAdapter<Param extends IRecyclerViewClickDelegate>
        extends RecyclerComplexClickBaseAdapter<Param, Param, Param> {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    public RecyclerDefaultAdapter(Context mContext, Collection<Param> list) {
        super(mContext, list);
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

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
