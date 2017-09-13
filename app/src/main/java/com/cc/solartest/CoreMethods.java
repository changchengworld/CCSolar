package com.cc.solartest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;

import java.util.Map;

/**
 * Created by silvercc on 17/9/12.
 */

public class CoreMethods {
    private static final String TAG = CoreMethods.class.getSimpleName();
    private volatile static CoreMethods mCoreMethods;
    private IRefreshListener mListener;
    private Context mCtx;
    private MyRunnable mRun;
    private Handler mHandler = new Handler(Looper.getMainLooper());

    private CoreMethods(Context context) {
        mCtx = context;
    }

    public static CoreMethods getInstance(Context context) {
        if (mCoreMethods == null) {
            synchronized (CoreMethods.class) {
                if (mCoreMethods == null) {
                    mCoreMethods = new CoreMethods(context);
                }
            }
        }
        return mCoreMethods;
    }

    public void setIRefreshListener(IRefreshListener listener) {
        mListener = listener;
    }

    public static CoreMethods getInstance() {
        if (mCoreMethods == null) {
            throw new IllegalArgumentException("先调有参数的实例化");
        }
        return mCoreMethods;
    }

    public void setParams(Map<String, String> map) {
        Log.i(TAG, "setParams");
        try {
            SharedPreferences sh = mCtx.getSharedPreferences("mobile_infos",
                    Context.MODE_WORLD_READABLE);
            SharedPreferences.Editor pre = sh.edit();
            if (map != null && !map.isEmpty()) {
                Log.d(TAG, "保存内容");
                String board = map.get(CoreKeys.BOARD);
                if (!TextUtils.isEmpty(board)) {
                    pre.putString(CoreKeys.BOARD, board);
                } else {
                    pre.putString(CoreKeys.BOARD, Build.BOARD);
                }
                Log.i(TAG, "BOARD:"+Build.BOARD);
                String brand = map.get(CoreKeys.BRAND);
                if (!TextUtils.isEmpty(brand)) {
                    pre.putString(CoreKeys.BRAND, brand);
                } else {
                    pre.putString(CoreKeys.BRAND, Build.BRAND);
                }
                Log.i(TAG, "BRAND:"+Build.BRAND);
                String cpu_abi = map.get(CoreKeys.CPU_ABI);
                if (!TextUtils.isEmpty(cpu_abi)) {
                    pre.putString(CoreKeys.CPU_ABI, cpu_abi);
                } else {
                    pre.putString(CoreKeys.CPU_ABI, Build.CPU_ABI);
                }
                Log.i(TAG, "CPU_ABI:"+Build.CPU_ABI);
                String device = map.get(CoreKeys.DEVICE);
                if (!TextUtils.isEmpty(device)) {
                    pre.putString(CoreKeys.DEVICE, device);
                } else {
                    pre.putString(CoreKeys.DEVICE, Build.DEVICE);
                }
                Log.i(TAG, "DEVICE:"+Build.DEVICE);
                String hardware = map.get(CoreKeys.HARDWARE);
                if (!TextUtils.isEmpty(hardware)) {
                    pre.putString(CoreKeys.HARDWARE, hardware);
                } else {
                    pre.putString(CoreKeys.HARDWARE, Build.HARDWARE);
                }
                Log.i(TAG, "HARDWARE:"+Build.HARDWARE);
                String manufacturer = map.get(CoreKeys.MANUFACTURER);
                if (!TextUtils.isEmpty(manufacturer)) {
                    pre.putString(CoreKeys.MANUFACTURER, manufacturer);
                } else {
                    pre.putString(CoreKeys.MANUFACTURER, Build.MANUFACTURER);
                }
                Log.i(TAG, "MANUFACTURER:"+Build.MANUFACTURER);
                String model = map.get(CoreKeys.MODEL);
                if (!TextUtils.isEmpty(model)) {
                    pre.putString(CoreKeys.MODEL, model);
                } else {
                    pre.putString(CoreKeys.MODEL, Build.MODEL);
                }
                Log.i(TAG, "MODEL:"+Build.MODEL);
                String product = map.get(CoreKeys.PRODUCT);
                if (!TextUtils.isEmpty(manufacturer)) {
                    pre.putString(CoreKeys.PRODUCT, product);
                } else {
                    pre.putString(CoreKeys.PRODUCT, Build.PRODUCT);
                }
                Log.i(TAG, "PRODUCT:"+Build.PRODUCT);
                String serial = map.get(CoreKeys.SERIAL);
                if (!TextUtils.isEmpty(serial)) {
                    pre.putString(CoreKeys.SERIAL, serial);
                } else {
                    pre.putString(CoreKeys.SERIAL, Build.SERIAL);
                }
                Log.i(TAG, "SERIAL:"+Build.SERIAL);
                pre.apply();
                mRun = new MyRunnable();
                mHandler.postDelayed(mRun, 2000);
            }
        } catch (Throwable e) {
            Log.d(TAG, "写入内容失败");
            e.printStackTrace();
        }
    }

    private class MyRunnable implements Runnable {
        @Override
        public void run() {
            if (mListener != null) {
                mListener.onRefresh(getParams());
            }
        }
    }

    public String getParams() {
        return Utils.getMobileInfos();
    }

    public void unregistListener() {
        mHandler.removeCallbacks(mRun);
        mListener = null;
    }
}
