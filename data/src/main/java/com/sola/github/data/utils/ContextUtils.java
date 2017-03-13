package com.sola.github.data.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.Pair;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by Sola
 * 2016/12/19.
 */
public class ContextUtils {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    private final WeakReference<Context> context;

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    ContextUtils(
            Context context
    ) {
        this.context = new WeakReference<>(context);
    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    public String getDeviceId() {
        String deviceId = "000000000000000";
        if (!android.os.Build.SERIAL.equalsIgnoreCase("unknown")) {
            try {
                TelephonyManager tm = (TelephonyManager) context.get().getSystemService(Context.TELEPHONY_SERVICE);
                deviceId = tm.getDeviceId();
//                com.google.
                if (deviceId == null || deviceId.isEmpty()) {
                    // 从数据库当中获取到设备id
                    deviceId = Settings.Secure.getString(context.get().getContentResolver(), Settings.Secure.ANDROID_ID);
                }
            } catch (SecurityException ignore) {
                ignore.printStackTrace();
            }
        }
        return deviceId;
    }

    public String getPackageName() {
        return context.get().getPackageName();
    }

    public PackageManager getPackageManager() {
        return context.get().getPackageManager();
    }

    public Pair<String, Integer> getVersionInfo() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException ignored) {
        }
        if (packageInfo != null) {
            return Pair.create(packageInfo.versionName, packageInfo.versionCode);
        }
        return null;
    }

    public String getAppChannelID() {
        String retStr = null;
        try {
            ApplicationInfo ai = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = ai.metaData;
            retStr = bundle.getString("CHANNEL_ID");
        } catch (PackageManager.NameNotFoundException ignored) {

        }
        return retStr;
    }


    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
