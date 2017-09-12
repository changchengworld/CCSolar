package com.cc.solartest;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by silvercc on 17/9/11.
 */

public class Main implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        changeBuildPro("arm64-v8a", "mt6735", "MSM8974", "msm8974", "msm8974", "Android", "vivo X710L", "Android", "199118");
//        findAndHookMethod("android.os.Build", lpparam.classLoader, "getString", new XC_MethodHook() {
//            @Override
//            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                // this will be called before the clock was updated by the original method
//                XposedBridge.log("开始劫持");
//                for (int i = 0; i < param.args.length; i++) {
//                    XposedBridge.log("参数" + i + " : " + param.args[i]);
//                }
//            }
//
//            @Override
//            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                // this will be called after the clock was updated by the original method
//                XposedBridge.log("劫持完成");
//                for (int i = 0; i < param.args.length; i++) {
//                    XposedBridge.log("参数" + i + " : " + param.args[i]);
//                }
//            }
//        });
    }

//    String str1 = Build.CPU_ABI + "_" + Build.CPU_ABI2;
//    String str2 = Build.HARDWARE + "_" + Build.BOARD + "_" + Build.DEVICE + "_" + Build.PRODUCT;
//    String str3 = Build.BRAND + "_" + Build.MODEL + "_" + Build.MANUFACTURER;
//    String str4 = "_" + Build.SERIAL + "_";
    public void changeBuildPro(String abi, String hardware, String board, String device, String product, String brand, String model, String manufacturer, String serial) {
        XposedBridge.log("开始修改配置");
        XposedHelpers.setStaticObjectField(android.os.Build.class,
                "CPU_ABI", abi);
        XposedHelpers.setStaticObjectField(android.os.Build.class,
                "HARDWARE", hardware);
        XposedHelpers.setStaticObjectField(android.os.Build.class,
                "BOARD", board);
        XposedHelpers.setStaticObjectField(android.os.Build.class,
                "DEVICE", device);
        XposedHelpers.setStaticObjectField(android.os.Build.class,
                "PRODUCT", product);
        XposedHelpers.setStaticObjectField(android.os.Build.class,
                "BRAND", brand);
        XposedHelpers.setStaticObjectField(android.os.Build.class,
                "MODEL", model);
        XposedHelpers.setStaticObjectField(android.os.Build.class,
                "MANUFACTURER", manufacturer);
        XposedHelpers.setStaticObjectField(android.os.Build.class,
                "SERIAL", serial);
    }
}
