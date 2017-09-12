package com.cc.solartest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by silvercc on 17/9/12.
 */

public class SuperService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    public void setParams(String serialNum) {

    }

    public String getParams() {
        return Utils.getMobileInfos();
    }
}
