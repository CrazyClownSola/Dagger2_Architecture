package com.sola.github.data.entity.net;

import com.google.gson.annotations.SerializedName;
import com.sola.github.tools.utils.StringUtils;

import java.util.Date;

/**
 * Created by Sola
 * 2016/12/19.
 */
@SuppressWarnings("unused")
public class BBSDataEntity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private int id;

    /**
     * 对应服务Id
     */
    private String serviceId;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片
     */
    private String pic;

    /**
     * 创造时间
     */
    private long createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 内容
     */
    private String content;

    @SerializedName("replyNum")
    private int replyCount;

    @SerializedName("todayReplyNum")
    private int todayReplyCount;

    private String appLink;

    private String appApkName;

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

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

//    public String getTimeStr() {
//        if (StringUtils.isEmpty(createTime))
//            return "";
//        else {
//            Date date = null;
//            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
//            try {
//                date = sdf.parse(createTime);
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//            return date == null ? createTime : StringUtils.getFormTime(date);
//        }
//    }

    public String getTimeStr() {
        return createTime <= 0 ? ("" + createTime) : StringUtils.getFormTime(new Date(createTime));
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public int getTodayReplyCount() {
        return todayReplyCount;
    }

    public void setTodayReplyCount(int todayReplyCount) {
        this.todayReplyCount = todayReplyCount;
    }

    public String getAppLink() {
        return appLink;
    }

    public void setAppLink(String appLink) {
        this.appLink = appLink;
    }

    public String getAppApkName() {
        return appApkName;
    }

    public void setAppApkName(String appApkName) {
        this.appApkName = appApkName;
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
