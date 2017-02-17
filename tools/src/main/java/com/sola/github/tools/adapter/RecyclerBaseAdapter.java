package com.sola.github.tools.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;

import com.sola.github.tools.delegate.IRecyclerViewDelegate;
import com.sola.github.tools.delegate.OnRecyclerItemDestroyListener;

import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhangluji
 * 2016/12/20.
 * 这种是不包括Headers和Footers的一种Adapter实现
 */
@SuppressWarnings("unused")
public class RecyclerBaseAdapter<Param extends IRecyclerViewDelegate>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // ===========================================================
    // Constants
    // ===========================================================

    private static final String TAG = "Sola";

    // ===========================================================
    // Fields
    // ===========================================================

    private final WeakReference<Context> mContext;

    private OnRecyclerItemClickListener<Param> listener;

    private List<Param> cacheList;

    /**
     * viewType 和 ViewHolder之间的关系序列
     * 由于考量到轻量化的问题，这里并没有以 {viewType : ViewHolder}的方式去做实现
     * 而是采用 {viewType : position}的方式，这里position表示viewType第一次出现的位置
     */
    private SparseIntArray typeRelationship = new SparseIntArray();

    // ===========================================================
    // Constructors
    // ===========================================================

    public RecyclerBaseAdapter(
            Context mContext,
            Collection<Param> list
    ) {
        this.mContext = new WeakReference<>(mContext);
        refreshList(list);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public void setListener(OnRecyclerItemClickListener<Param> listener) {
        this.listener = listener;
    }

    public List<Param> getCacheList() {
        return cacheList;
    }

    public Context getContext() {
        return mContext.get();
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 可以看出这里主要是通过不同的类型在cache的数据当中找到对应的代理
        IRecyclerViewDelegate delegate = getItemByViewType(viewType);
        if (delegate == null)
            return null;
        // 返回代理中所实现的ViewHolder
        return delegate.getHolder(mContext.get(), parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        // 这里思路和ListView，BindData的思路很像，找到数组中对应position的数据进行数据绑定
        if (position < 0 || position >= cacheList.size())
            return;
        Param item = cacheList.get(position);
        if (item == null)
            return;
        item.refreshView(mContext.get(), holder, position);
        if (listener != null)
            holder.itemView.setOnClickListener(v -> listener.onClick(v, item));
    }

    @Override
    public int getItemCount() {
        return cacheList == null ? 0 : cacheList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (cacheList == null || cacheList.size() == 0)
            return -1;
        else {
            int viewType = cacheList.get(position).getViewType(position);
            if (typeRelationship.indexOfKey(viewType) < 0) {
                // 入口处，注意这里的实现是确保唯一性，只有第一次发现新的ViewType的时候才记录当前位置
                typeRelationship.put(viewType, position);
            }
            return viewType;
        }
    }

    // ===========================================================
    // Methods
    // ===========================================================

    /**
     * @param params 刷新适配器当中所用到的集合数据
     */
    public final void refreshList(Collection<Param> params) {
        destroyAll();
        if (params == null || params.size() == 0) {
            cacheList = new LinkedList<>();
        } else {
            if (cacheList == null)
                cacheList = new LinkedList<>();
            cacheList.clear();
            cacheList.addAll(params);
        }
        notifyDataSetChanged();
    }

    private void destroyAll() {
        if (cacheList == null || cacheList.size() == 0)
            return;
        for (Param item :
                cacheList) {
            if (item != null && item instanceof OnRecyclerItemDestroyListener)
                ((OnRecyclerItemDestroyListener) item).onDestroy();
        }
        typeRelationship.clear();
    }

    private IRecyclerViewDelegate getItemByViewType(int viewType) {
        int index = typeRelationship.get(viewType);
        return index == -1 ? null : cacheList.get(index);
    }

    Param getItemByPosition(int position) {
        return getItemCount() == 0 ? null : cacheList.get(position);
    }

    public void addItem(Param item) {
        if (cacheList == null)
            return;
        cacheList.add(item);
        notifyItemInserted(cacheList.indexOf(item));
    }

    public void addItem(Param item, int position) {
        if (cacheList == null)
            return;
        cacheList.add(position, item);
        notifyItemInserted(cacheList.indexOf(item));
    }

    public void replacePosition(Param item, int position) {
        if (cacheList == null)
            return;
        if (cacheList.get(position) != null) {
            cacheList.remove(position);
            cacheList.add(position, item);
        } else
            cacheList.add(item);
        notifyItemInserted(cacheList.indexOf(item));
    }


    /**
     * 清空列表
     */
    public void clear() {
        if (cacheList == null || cacheList.isEmpty())
            return;
        cacheList.clear();
        typeRelationship.clear();
        notifyDataSetChanged();
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    public interface OnRecyclerItemClickListener<Param> {
        void onClick(View v, Param viewDto);
    }

}
