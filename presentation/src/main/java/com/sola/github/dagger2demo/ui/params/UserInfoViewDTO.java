package com.sola.github.dagger2demo.ui.params;

import android.content.Context;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.sola.github.dagger2demo.R;
import com.sola.github.domain.params.params.uc.UserInfoDTO;

/**
 * Created by Sola
 * 2017/2/22.
 */
public class UserInfoViewDTO extends BaseViewDTO<UserInfoDTO> {

    // ===========================================================
    // Constants
    // ===========================================================

    private final static String TAG = "Sola";

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    public UserInfoViewDTO(UserInfoDTO data) {
        super(data);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public RecyclerView.ViewHolder getHolder(Context context, ViewGroup parent, int viewType) {
        BaseHolder holder = new BaseHolder(LayoutInflater.from(context).inflate(
                R.layout.recycler_item_user_info, parent, false));
        holder.getBinding().addOnRebindCallback(new OnRebindCallback<ViewDataBinding>() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                Log.d(TAG, "onPreBind() called with: binding = [" + binding + "]");
                return super.onPreBind(binding);
            }

            @Override
            public void onCanceled(ViewDataBinding binding) {
                Log.w(TAG, "onCanceled() called with: binding = [" + binding + "]");
                super.onCanceled(binding);
            }

            @Override
            public void onBound(ViewDataBinding binding) {
                Log.i(TAG, "onBound() called with: binding = [" + binding + "]");
                super.onBound(binding);
            }
        });
        return holder;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
