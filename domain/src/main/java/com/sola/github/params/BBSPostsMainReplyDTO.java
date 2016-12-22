package com.sola.github.params;

import java.util.List;

/**
 * Created by zhangluji
 * 2016/12/21.
 * 帖子中，单个回复的主体，当中包含一些子回复
 */
@SuppressWarnings("unused")
public class BBSPostsMainReplyDTO extends BBSPostsBaseDTO {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private int status;

    private int likeCount;

    private int replyCount;

    private List<BBSPostsSingleReplyDTO> replyList;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public List<BBSPostsSingleReplyDTO> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<BBSPostsSingleReplyDTO> replyList) {
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
