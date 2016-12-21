package com.sola.github.data.entity.net;

import com.google.gson.annotations.SerializedName;
import com.sola.github.tools.utils.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhangluji
 * 2016/12/21.
 */
@SuppressWarnings("unused")
class BBSPostsBaseEntity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private int id;

    private String content;

    private String createTime;

    private String updateTime;

    private String userId;

    private String userName;

    private int bbsId;

    private int postsId;

    @SerializedName("userAvatar")
    private String userPic;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getBbsId() {
        return bbsId;
    }

    public void setBbsId(int bbsId) {
        this.bbsId = bbsId;
    }

    public int getPostsId() {
        return postsId;
    }

    public void setPostsId(int postsId) {
        this.postsId = postsId;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public long getTimestamp() {
        if (StringUtils.isEmpty(createTime))
            return 0;
        else {
            Date date = null;
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            try {
                date = sdf.parse(createTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date == null ? 0 : date.getTime();
        }
    }

    public String getTimeStr() {
        if (StringUtils.isEmpty(createTime))
            return "";
        else {
            Date date = null;
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            try {
                date = sdf.parse(createTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date == null ? createTime : StringUtils.getFormTime(date);
        }
    }

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
