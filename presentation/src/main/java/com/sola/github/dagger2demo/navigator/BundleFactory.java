package com.sola.github.dagger2demo.navigator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Sola
 * 2016/12/23.
 */
@Singleton
@SuppressWarnings("unused")
public class BundleFactory {
    // ===========================================================
    // Constants
    // ===========================================================

    private final static String TAG = "Sola/BundleFactory";

    // ===========================================================
    // Fields
    // ===========================================================

    private final Bundle mBundle;

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    BundleFactory() {
        this.mBundle = new Bundle();
        Log.d(TAG, "BundleFactory() called");
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public Bundle build() {
        return mBundle;
    }

    public BundleFactory putString(String key, String value) {
        mBundle.putString(key, value);
        return this;
    }

    public BundleFactory putBoolean(String key, Boolean value) {
        mBundle.putBoolean(key, value);
        return this;
    }

    public BundleFactory putBundle(@Nullable String key, Bundle bundle) {
        if (key == null || key.isEmpty())
            mBundle.putAll(bundle);
        else
            mBundle.putBundle(key, bundle);
        return this;
    }

    public BundleFactory putByte(String key, byte... value) {
        if (value != null && value.length == 1)
            mBundle.putByte(key, value[0]);
        else
            mBundle.putByteArray(key, value);
        return this;
    }

    public BundleFactory putFloat(String key, float... value) {
        if (value != null && value.length == 1)
            mBundle.putFloat(key, value[0]);
        else
            mBundle.putFloatArray(key, value);
        return this;
    }

    public BundleFactory putInt(String key, int... value) {
        if (value != null && value.length == 1)
            mBundle.putInt(key, value[0]);
        else
            mBundle.putIntArray(key, value);
        return this;
    }

    public BundleFactory putSerializable(String key, Serializable value) {
        mBundle.putSerializable(key, value);
        return this;
    }

    public BundleFactory putChar(String key, char... value) {
        if (value != null && value.length == 1)
            mBundle.putChar(key, value[0]);
        else
            mBundle.putCharArray(key, value);
        return this;
    }

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
