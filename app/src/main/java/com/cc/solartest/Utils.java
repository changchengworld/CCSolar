package com.cc.solartest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by silvercc on 17/9/12.
 */

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    public static String getMobileInfos() {
        String result = "{" + "\"CPU_ABI\":" + Build.CPU_ABI +
                ", \"HARDWARE\":" + Build.HARDWARE + ", \"BOARD\":" + Build.BOARD + ", \"DEVICE\":" + Build.DEVICE + ", \"PRODUCT\":" + Build.PRODUCT +
                ", \"BRAND\":" + Build.BRAND + ", \"MODEL\":" + Build.MODEL + ", \"MANUFACTURER\":" + Build.MANUFACTURER +
                ", \"SERIAL\":" + Build.SERIAL + "}";
        Log.i(TAG, result);
        return result;
    }

    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }

    public static void showToast(String content) {
        Context context = SolarApplication.getInstance().getApplicationContext();
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }

}
