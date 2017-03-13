package com.sola.github.data.entity.net;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Sola
 * 2016/12/21.
 */
@SuppressWarnings("unused")
public class BBSPostsEntity extends BBSPostsBaseEntity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private int status;

    @SerializedName("likes")
    private int likeCount;

    @SerializedName("replys")
    private int replyCount;

    private List<BBSPostsReplyEntity> replyList;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public int getStatus() {
        return status;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public List<BBSPostsReplyEntity> getReplyList() {
        return replyList;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public void setReplyList(List<BBSPostsReplyEntity> replyList) {
        this.replyList = replyList;
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
