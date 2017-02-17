package com.sola.github.dagger2demo.enums;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.sola.github.dagger2demo.enums.ESubType.TYPE_ACTIVITY;
import static com.sola.github.dagger2demo.enums.ESubType.TYPE_CACHE;
import static com.sola.github.dagger2demo.enums.ESubType.TYPE_DB;

/**
 * Created by slove
 * 2016/12/14.
 */
@IntDef({TYPE_DB, TYPE_CACHE, TYPE_ACTIVITY})
@Retention(RetentionPolicy.SOURCE)
public @interface ESubType {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    int TYPE_DB = 0;

    int TYPE_CACHE = 1;

    int TYPE_ACTIVITY = 2;

    // ===========================================================
    // Constructors
    // ===========================================================

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

}
