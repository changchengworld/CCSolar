package com.cc.solartest;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by silvercc on 17/9/12.
 */

public class SolarService extends Service {
    private SolarBroadCastReceiver mReceiver;
    private Map<String, String> mMap;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mReceiver = new SolarBroadCastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("CC.Solar");
        registerReceiver(mReceiver, filter);
        mMap = new HashMap<>();
        return super.onStartCommand(intent, flags, startId);
    }

    class SolarBroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("CC.Solar")) {
                mMap.put(CoreKeys.SERIAL, intent.getStringExtra(CoreKeys.SERIAL));
                mMap.put(CoreKeys.BOARD, intent.getStringExtra(CoreKeys.BOARD));
                mMap.put(CoreKeys.BRAND, intent.getStringExtra(CoreKeys.BRAND));
                mMap.put(CoreKeys.CPU_ABI, intent.getStringExtra(CoreKeys.CPU_ABI));
                mMap.put(CoreKeys.DEVICE, intent.getStringExtra(CoreKeys.DEVICE));
                mMap.put(CoreKeys.HARDWARE, intent.getStringExtra(CoreKeys.HARDWARE));
                mMap.put(CoreKeys.MANUFACTURER, intent.getStringExtra(CoreKeys.MANUFACTURER));
                mMap.put(CoreKeys.MODEL, intent.getStringExtra(CoreKeys.MODEL));
                mMap.put(CoreKeys.PRODUCT, intent.getStringExtra(CoreKeys.PRODUCT));
                CoreMethods.getInstance().setParams(mMap);
            }
        }
    }

    @Override
    public void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }
}
