package com.sola.github.tools.delegate;

import android.view.View;

/**
 * Created by zhangluji
 * 2016/12/20.
 */
public interface IRecyclerViewClickDelegate extends IRecyclerViewDelegate {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * item点击事件
     *
     * @param v 点击的view
     */
    void itemClick(View v);
}
