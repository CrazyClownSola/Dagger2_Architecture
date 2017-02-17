package com.sola.github.data.entity;

import com.google.gson.annotations.SerializedName;
import com.sola.github.tools.utils.StringUtils;

/**
 * Created by zhangluji
 * 2016/12/19.
 */
@SuppressWarnings("unused")
public class BaseResultEntity {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    @SerializedName("errorMsg")
    private String message;

    @SerializedName("responseCode")
    private String code;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public boolean isSuccess() {
        return !StringUtils.isEmpty(code) && code.equalsIgnoreCase("1000");
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
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
