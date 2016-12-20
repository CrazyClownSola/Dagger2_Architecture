package com.sola.github.dagger2demo.ui.params;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sola.github.params.BBSDataDTO;
import com.sola.github.tools.delegate.IRecyclerViewClickDelegate;

/**
 * Created by zhangluji
 * 2016/12/20.
 */
@SuppressWarnings("unused")
public class BBSDataViewDTO extends BaseViewDTO<BBSDataDTO> implements IRecyclerViewClickDelegate {

    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    public BBSDataViewDTO(BBSDataDTO data) {
        super(data);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public void itemClick(View v) {

    }

    @Override
    public View getView(Context context, ViewGroup parent) {
        return null;
    }

    @Override
    public RecyclerView.ViewHolder getHolder(Context context, ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void refreshView(Context context, RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getViewType(int position) {
        return 0;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
