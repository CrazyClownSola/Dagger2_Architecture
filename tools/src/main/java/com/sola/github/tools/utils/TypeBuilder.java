package com.sola.github.tools.utils;

import java.util.Random;

/**
 * Created by zhangluji
 * 2016/12/21.
 */
@SuppressWarnings("unused")
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

    private final Random random;

    // ===========================================================
    // Constructors
    // ===========================================================

    private TypeBuilder() {
        cacheType = DEFAULT_INIT_TYPE;
        random = new Random();
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

    /**
     * @return 生成随机ID
     */
    public int generateRanId() {
        return random.nextInt();
    }

    /**
     * @return 生成随机ID
     */
    public int generateRanId(int n) {
        return random.nextInt(n);
    }
    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
