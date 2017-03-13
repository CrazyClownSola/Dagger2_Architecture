package com.sola.github.tools.delegate;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Sola
 * 2016/12/20.
 */
public interface IRecyclerViewDelegate {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

//    /**
//     * 获取布局
//     *
//     * @param context context容器
//     * @param parent  父类布局
//     * @return 返回布局View
//     */
//    View getView(Context context, ViewGroup parent);

    /**
     * 构建Holder
     *
     * @param context  context容器
     * @param parent   父类布局
     * @param viewType 布局类型
     * @return 返回RecyclerView需要的ViewHolder
     */
    RecyclerView.ViewHolder getHolder(Context context, ViewGroup parent, int viewType);

    /**
     * 刷新布局
     *
     * @param context  context容器
     * @param holder   view容器
     * @param position 序列位置
     */
    void refreshView(Context context, RecyclerView.ViewHolder holder, int position);


    /**
     * 注意该方法的使用，主要区分在于当RecyclerView的Item项，每一项都不相同的时候，
     * 可以根据不同的ViewType进行界面的适配;
     * 但是当每个项类型都相同的时候，这个方法返回的值就不那么重要了
     *
     * @param position 在适配器中的位置,注意这个参数在作为Header加入的时候这个值为-1
     * @return 范围该项所对应的布局类型
     */
    int getViewType(int position);
}
