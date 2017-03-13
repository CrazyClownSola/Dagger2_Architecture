package com.sola.github.data.net;

import android.content.Context;

import com.sola.github.data.utils.ContextUtils;

import java.lang.ref.WeakReference;

/**
 * Created by Sola
 * 2017/2/22.
 */
public abstract class AApiConnection {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final WeakReference<Context> context;

    private final ContextUtils contextUtils;

    // ===========================================================
    // Constructors
    // ===========================================================

    AApiConnection(Context context, ContextUtils contextUtils) {
        this.context = new WeakReference<>(context);
        this.contextUtils = contextUtils;
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    public Context getContext() {
        return context.get();
    }

    ContextUtils getContextUtils() {
        return contextUtils;
    }

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    // ===========================================================
    // Methods
    // ===========================================================

    public abstract <S> S createService(String baseUrl, Class<S> serviceCls);

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
