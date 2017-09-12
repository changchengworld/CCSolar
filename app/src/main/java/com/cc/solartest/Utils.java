package com.cc.solartest;

import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silvercc on 17/9/12.
 */

public class Utils {
    private static List<String> strs;

    public Utils() {
        strs = new ArrayList<String>();
    }

    public static String getMobileInfos(){
        String str1 = Build.CPU_ABI + "_" + Build.CPU_ABI2;
        String str2 = Build.HARDWARE + "_" + Build.BOARD + "_" + Build.DEVICE + "_" + Build.PRODUCT;
        String str3 = Build.BRAND + "_" + Build.MODEL + "_" + Build.MANUFACTURER;
        String str4 = "_" + Build.SERIAL + "_";
        return str1 + "|" + str2 + "|" + str3 + "|" + str4;
    }

}
