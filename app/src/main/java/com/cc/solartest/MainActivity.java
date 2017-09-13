package com.cc.solartest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IRefreshListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView viewById;
    private EditText et_serial, et_cpu_abi, et_hardware, et_board, et_device, et_product, et_brand, et_model, et_manufacture;
    private Map<String, String> mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewById = (TextView) findViewById(R.id.tv);
        et_cpu_abi = (EditText) findViewById(R.id.et_cpu_abi);
        et_hardware = (EditText) findViewById(R.id.et_hardware);
        et_board = (EditText) findViewById(R.id.et_board);
        et_device = (EditText) findViewById(R.id.et_device);
        et_product = (EditText) findViewById(R.id.et_product);
        et_brand = (EditText) findViewById(R.id.et_brand);
        et_model = (EditText) findViewById(R.id.et_model);
        et_manufacture = (EditText) findViewById(R.id.et_manufacture);
        et_serial = (EditText) findViewById(R.id.et_serial);
        Button bt_serial = (Button) findViewById(R.id.bt_serial);
        CoreMethods.getInstance(this).setIRefreshListener(this);
        bt_serial.setOnClickListener(this);
        viewById.setText(CoreMethods.getInstance().getParams());
        mMap = new HashMap<>();
        Intent intent = new Intent(this, SolarService.class);
        startService(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_serial:
                if (mMap != null) {
                    mMap.put(CoreKeys.CPU_ABI, et_cpu_abi.getText().toString());
                    mMap.put(CoreKeys.HARDWARE, et_hardware.getText().toString());
                    mMap.put(CoreKeys.BOARD, et_board.getText().toString());
                    mMap.put(CoreKeys.DEVICE, et_device.getText().toString());
                    mMap.put(CoreKeys.PRODUCT, et_product.getText().toString());
                    mMap.put(CoreKeys.BRAND, et_brand.getText().toString());
                    mMap.put(CoreKeys.MODEL, et_model.getText().toString());
                    mMap.put(CoreKeys.MANUFACTURER, et_manufacture.getText().toString());
                    mMap.put(CoreKeys.SERIAL, et_serial.getText().toString());
                    CoreMethods.getInstance().setParams(mMap);
                    Utils.showToast("change mobile_infos success");
                } else {
                    Utils.showToast("change mobile_infos fail");
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, SolarService.class);
        stopService(intent);
        CoreMethods.getInstance().unregistListener();
        super.onDestroy();
    }

    @Override
    public void onRefresh(String mobileinfos) {
        Utils.showToast("get mobile_infos success");
        viewById.setText(mobileinfos);
    }
}
