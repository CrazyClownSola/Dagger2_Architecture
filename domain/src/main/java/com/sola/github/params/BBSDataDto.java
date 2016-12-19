package com.sola.github.params;

/**
 * Created by zhangluji
 * 2016/12/19.
 */
public class BBSDataDTO extends BBSBaseDTO {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    /**
     * 回复数量
     */
    private int replyCount;

    /**
     * 点赞数量
     */
    private int likeCount;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
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
