package com.sola.github.params;

import java.util.List;

/**
 * Created by zhangluji
 * 2016/12/21.
 */
@SuppressWarnings("unused")
public class BBSPostsDTO extends BBSPostsBaseDTO {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private int status;

    private int likeCount;

    private int replyCount;

    private List<BBSPostsReplyDTO> replyList;

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

    public List<BBSPostsReplyDTO> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<BBSPostsReplyDTO> replyList) {
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
