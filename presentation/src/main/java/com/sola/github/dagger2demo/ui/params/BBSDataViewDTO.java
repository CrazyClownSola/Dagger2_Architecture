package com.sola.github.dagger2demo.ui.params;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sola.github.dagger2demo.R;
import com.sola.github.params.BBSDataDTO;
import com.sola.github.tools.delegate.IRecyclerViewClickDelegate;
import com.sola.github.tools.utils.StringUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by zhangluji
 * 2016/12/21.
 * 帖子的View结构，和帖子数据结构进行绑定
 */
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
        // 跳转Item详情界面的点击事件

    }

    @Override
    public RecyclerView.ViewHolder getHolder(Context context, ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_bbs_data_view, parent, false));
    }

    @Override
    public void refreshView(Context context, RecyclerView.ViewHolder holder, int position) {
        if (data == null)
            return; // 这样做会在界面复用的时候有一些问题
        ViewHolder viewHolder = (ViewHolder) holder;
        if (!StringUtils.isEmpty(data.getPic()))
            Picasso.with(context).load(data.getPic())
                    .centerCrop()
                    .resize(50, 50)
                    .into(viewHolder.id_image_user_photo);
        viewHolder.id_text_bbs_title.setText(data.getTitle());
        viewHolder.id_text_bbs_content.setText(data.getContent());
        viewHolder.id_text_bbs_create_time.setText(data.getDisplayTime());
        viewHolder.id_text_bbs_comment_count.setText(
                context.getString(
                        R.string.string_html_bbs_data_reply_count,
                        data.getReplyCount()));
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

    private class ViewHolder extends RecyclerView.ViewHolder {

        ImageView id_image_user_photo;

        TextView id_text_bbs_user_name, id_text_bbs_title, id_text_bbs_content,
                id_text_bbs_create_time, id_text_bbs_comment_count;

        ViewHolder(View itemView) {
            super(itemView);
            // 这里不用BindView的原因在于，BindView的使用需要public这个ViewHolder，这样会有一个警告，看上去不爽
            id_image_user_photo = (ImageView) itemView.findViewById(R.id.id_image_user_photo);
            id_text_bbs_user_name = (TextView) itemView.findViewById(R.id.id_text_bbs_user_name);
            id_text_bbs_title = (TextView) itemView.findViewById(R.id.id_text_bbs_title);
            id_text_bbs_content = (TextView) itemView.findViewById(R.id.id_text_bbs_content);
            id_text_bbs_create_time = (TextView) itemView.findViewById(R.id.id_text_bbs_create_time);
            id_text_bbs_comment_count = (TextView) itemView.findViewById(R.id.id_text_bbs_comment_count);
        }
    }

}
