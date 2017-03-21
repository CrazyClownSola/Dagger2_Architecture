package com.sola.github.dagger2demo.ui.params;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sola.github.dagger2demo.R;
import com.sola.github.dagger2demo.databinding.RecyclerItemUserInfoBinding;
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
//    private RecyclerItemUserInfoBinding binding;
    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    @Override
    public RecyclerView.ViewHolder getHolder(Context context, ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_user_info, parent, false));
//        return new ViewHolder(binding);
    }

    @Override
    public void refreshView(Context context, RecyclerView.ViewHolder holder, int position) {
        Log.d(TAG, "refreshView: position[" + position + "]");
        if (data == null)
            return;
        ViewHolder viewHolder = (ViewHolder) holder;
//        viewHolder.binding.setText(data.getName());
        viewHolder.binding.setUserInfo(data);
        // 这段代码很重要，有点强制刷新的意思，如果这段代码不加，binding会被推出到下一帧，这样会是的界面无限刷新
        // 建议配置到ViewHolder里面去
        viewHolder.binding.executePendingBindings();

    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    class ViewHolder extends RecyclerView.ViewHolder {

        RecyclerItemUserInfoBinding binding;

        ViewHolder(View itemView) {
            super(itemView);
            this.binding = DataBindingUtil.bind(itemView);
        }
    }

}
