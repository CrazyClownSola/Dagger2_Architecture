package com.sola.github.dagger2demo.ui.params;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_user_info, parent, false));
    }

    @Override
    public void refreshView(Context context, RecyclerView.ViewHolder holder, int position) {
        if (data == null)
            return;
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.id_text_user_name.setText(data.getName());
        viewHolder.id_text_user_age.setText(getResourceStr(context, R.string.str_user_info_age, data.getAge()));
        viewHolder.id_text_user_do_something.setText("do something");
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView id_text_user_name,
                id_text_user_age,
                id_text_user_do_something;

        ViewHolder(View itemView) {
            super(itemView);
            id_text_user_name = (TextView) itemView.findViewById(R.id.id_text_user_name);
            id_text_user_age = (TextView) itemView.findViewById(R.id.id_text_user_age);
            id_text_user_do_something = (TextView) itemView.findViewById(R.id.id_text_user_do_something);
        }
    }

}
