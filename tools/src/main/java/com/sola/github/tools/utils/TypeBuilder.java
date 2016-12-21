package com.sola.github.tools.utils;

/**
 * Created by zhangluji
 * 2016/12/21.
 */
public class TypeBuilder {
    // ===========================================================
    // Constants
    // ===========================================================

    private final static int DEFAULT_INIT_TYPE = 0x0001;

    // ===========================================================
    // Fields
    // ===========================================================

    private int cacheType;

    private static TypeBuilder instance;

    // ===========================================================
    // Constructors
    // ===========================================================

    private TypeBuilder() {
        cacheType = DEFAULT_INIT_TYPE;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public static TypeBuilder getInstance() {
        if (instance == null)
            instance = new TypeBuilder();
        return instance;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    public int generateId() {
        return ++cacheType;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
