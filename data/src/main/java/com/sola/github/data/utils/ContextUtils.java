package com.sola.github.data.utils;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import javax.inject.Inject;

/**
 * Created by zhangluji
 * 2016/12/19.
 */
public class ContextUtils {
    // ===========================================================
    // Constants
    // ===========================================================

    // ===========================================================
    // Fields
    // ===========================================================

    // ===========================================================
    // Constructors
    // ===========================================================

    @Inject
    ContextUtils() {

    }

    // ===========================================================
    // Getter & Setter
    // ===========================================================

    // ===========================================================
    // Methods for/from SuperClass/Interfaces
    // ===========================================================

    public String getDeviceId(Context context) {
        String deviceId = "000000000000000";
        if (!android.os.Build.SERIAL.equalsIgnoreCase("unknown")) {
            try {
                TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                deviceId = tm.getDeviceId();
                if (deviceId == null || deviceId.isEmpty()) {
                    // 从数据库当中获取到设备id
                    deviceId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
                }
            } catch (SecurityException ignore) {
                ignore.printStackTrace();
            }
        }
        return deviceId;
    }

    // ===========================================================
    // Methods
    // ===========================================================

    // ===========================================================
    // Inner and Anonymous Classes
    // ===========================================================

}
