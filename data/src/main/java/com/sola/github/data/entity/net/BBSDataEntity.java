package com.sola.github.data.entity.net;

/**
 * Created by zhangluji
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
    private String createTime;

    /**
     * 更新时间
     */
    private String updateTime;

    /**
     * 内容
     */
    private String content;

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
