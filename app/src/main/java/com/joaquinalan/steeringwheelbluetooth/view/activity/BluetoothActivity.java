package com.joaquinalan.steeringwheelbluetooth.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.joaquinalan.steeringwheelbluetooth.R;

public class BluetoothActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        Toolbar toolbarContactDetail = (Toolbar) findViewById(R.id.toolbar_bluetooth);
        setSupportActionBar(toolbarContactDetail);
        getSupportActionBar().setTitle(R.string.bluetooth_toolbartitle);
        getSupportActionBar().setIcon(R.drawable.ic_main_bluetooth);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
