package com.cc.solartest;

import java.util.Map;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by silvercc on 17/9/11.
 */

public class Main implements IXposedHookLoadPackage {
    private static final String TAG = Main.class.getSimpleName();

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        HookMethod(lpparam, "setParams");
    }

    private void HookMethod(XC_LoadPackage.LoadPackageParam lpparam, String method) {
        if (!lpparam.packageName.equals("com.cc.solartest"))
            return;
        XposedBridge.log("HookMethod");
        XposedHelpers.findAndHookMethod("com.cc.solartest.CoreMethods", lpparam.classLoader, method, Map.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                XSharedPreferences pre = new XSharedPreferences(this.getClass()
                        .getPackage().getName(), "mobile_infos");
                XposedHelpers.setStaticObjectField(android.os.Build.class,
                        "CPU_ABI", pre.getString("CPU_ABI", ""));
                XposedHelpers.setStaticObjectField(android.os.Build.class,
                        "HARDWARE", pre.getString("HARDWARE", ""));
                XposedHelpers.setStaticObjectField(android.os.Build.class,
                        "BOARD", pre.getString("BOARD", ""));
                XposedHelpers.setStaticObjectField(android.os.Build.class,
                        "DEVICE", pre.getString("DEVICE", ""));
                XposedHelpers.setStaticObjectField(android.os.Build.class,
                        "PRODUCT", pre.getString("PRODUCT", ""));
                XposedHelpers.setStaticObjectField(android.os.Build.class,
                        "BRAND", pre.getString("BRAND", ""));
                XposedHelpers.setStaticObjectField(android.os.Build.class,
                        "MODEL", pre.getString("MODEL", ""));
                XposedHelpers.setStaticObjectField(android.os.Build.class,
                        "MANUFACTURER", pre.getString("MANUFACTURER", ""));
                XposedHelpers.setStaticObjectField(android.os.Build.class,
                        "SERIAL", pre.getString("SERIAL", ""));
            }
        });
    }
}
