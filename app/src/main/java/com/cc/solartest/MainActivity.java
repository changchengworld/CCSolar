package com.cc.solartest;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = (TextView) findViewById(R.id.tv);
        Button bt_refresh = (Button) findViewById(R.id.bt_refresh);
        Button bt_start_service = (Button) findViewById(R.id.bt_start_service);
        Button bt_stop_service = (Button) findViewById(R.id.bt_stop_service);
        bt_refresh.setOnClickListener(this);
        bt_start_service.setOnClickListener(this);
        bt_stop_service.setOnClickListener(this);
        viewById.setText(getCharSequence());
    }

    private String getCharSequence() {
        String str1 = Build.CPU_ABI + "_" + Build.CPU_ABI2;
        String str2 = Build.HARDWARE + "_" + Build.BOARD + "_" + Build.DEVICE + "_" + Build.PRODUCT;
        String str3 = Build.BRAND + "_" + Build.MODEL + "_" + Build.MANUFACTURER;
        String str4 = "_" + Build.SERIAL + "_";
        return str1 + "|" + str2 + "|" + str3 + "|" + str4;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, SuperService.class);
        switch (v.getId()) {
            case R.id.bt_refresh:
                viewById.setText(getCharSequence());
                break;
            case R.id.bt_start_service:
                startService(intent);
                break;
            case R.id.bt_stop_service:
                stopService(intent);
                break;
            default:
                break;
        }
    }
}
