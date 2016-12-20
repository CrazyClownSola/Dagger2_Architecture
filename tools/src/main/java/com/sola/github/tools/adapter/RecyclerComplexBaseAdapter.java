package com.sola.github.tools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.sola.github.tools.delegate.IRecyclerViewDelegate;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangluji
 * 2016/12/20.
 * RecyclerView在实现的时候是不涉及到Header和Footer的，有很多时候我们需要使用到这两个东西
 * 这里就提供一种做法
 * 思路很简单，把header和Footer独立出来
 */
@SuppressWarnings("unused")
public class RecyclerComplexBaseAdapter<
        Param extends IRecyclerViewDelegate,
        Header extends IRecyclerViewDelegate,
        Footer extends IRecyclerViewDelegate>
        extends RecyclerBaseAdapter<Param> {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private List<Header> headers;

    private List<Footer> footers;

    // ===========================================================
    // Constructors
    // ===========================================================

    public RecyclerComplexBaseAdapter(Context mContext, Collection<Param> list) {
        super(mContext, list);
        headers = new LinkedList<>();
        footers = new LinkedList<>();
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (checkIsInHeader(position)) {
            headers.get(position).refreshView(getContext(), holder, position);
        } else if (checkIsInFooter(position)) {
            footers.get(position - getFooterStartPosition()).
                    refreshView(getContext(), holder, position);
        } else
            super.onBindViewHolder(holder, getTruePosition(position));
    }

    @Override
    public int getItemCount() {
        int count = headers.size();
        count += super.getItemCount();
        count += footers.size();
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (checkIsInHeader(position)) {
            return headers.get(position).getViewType(position);
        } else if (checkIsInFooter(position)) {
            return footers.get(position - getFooterStartPosition()).getViewType(position);
        } else
            return super.getItemViewType(getTruePosition(position));
    }

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * @param position 当前项在总体项中的位置
     * @return 返回删减掉头部数量的，数据内容的位置
     */
    private int getTruePosition(int position) {
        return position - headers.size();
    }

    private int getFooterStartPosition() {
        return headers.size() + super.getItemCount();
    }

    private boolean checkIsInHeader(int position) {
        return position < headers.size();
    }

    private boolean checkIsInFooter(int position) {
        return position >= getFooterStartPosition();
    }

    public void addHeaderView(Header header) {
        headers.add(header);
        notifyHeaderViews();
    }

    public void addHeaderView(int position, Header header) {
        if (headers.size() > position) {
            Header oldItem = headers.get(position);
            headers.set(position, header);
            if (oldItem != null) {
                headers.add(position + 1, oldItem);
            }
        } else
            headers.add(header);
        notifyHeaderViews();
    }

    private void notifyHeaderViews() {
        for (int i = 0; i < headers.size(); i++) {
            notifyItemChanged(i);
        }
    }

    public void removeHeaderView(Header header) {
        int indexToRemove = headers.indexOf(header);
        if (indexToRemove >= 0) {
            headers.remove(indexToRemove);
            notifyItemRemoved(indexToRemove);
        }
    }

    public void addFooterView(Footer footerView) {
        footers.add(footerView);
        notifyItemInserted(getItemCount());
    }

    public void removeFooterView(Footer footerView) {
        int indexToRemove = footers.indexOf(footerView);
        if (indexToRemove >= 0) {
            footers.remove(indexToRemove);
            notifyItemRemoved(getFooterStartPosition() + indexToRemove);
        }
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
