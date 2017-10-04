package com.joaquinalan.steeringwheelbluetooth.view.activity;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joaquinalan.steeringwheelbluetooth.R;
import com.joaquinalan.steeringwheelbluetooth.broadcastreceiver.BroadcastDeviceFound;
import com.joaquinalan.steeringwheelbluetooth.broadcastreceiver.BroadcastStateBluetooth;
import com.joaquinalan.steeringwheelbluetooth.presenter.BluetoothPresenter;
import com.joaquinalan.steeringwheelbluetooth.presenter.MvpBluetoothPresenter;
import com.joaquinalan.steeringwheelbluetooth.view.BluetoothView;
import com.joaquinalan.steeringwheelbluetooth.view.adapter.FoundDevicesAdapter;

import java.util.List;

public class BluetoothActivity extends AppCompatActivity implements View.OnClickListener, BluetoothView, FoundDevicesAdapter.ListItemClickListener {
    private RecyclerView mRecyclerViewFoundDevices;
    private FoundDevicesAdapter mFoundDevicesAdapter;
    private MvpBluetoothPresenter mPresenter;
    private View mViewForSnackbar;
    private BroadcastReceiver mBroadcastDeviceFound;
    private BroadcastReceiver mBroadcastStateBluetooth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        FloatingActionButton mFloatingActionButtonToogleBluetooth;
        mFloatingActionButtonToogleBluetooth = (FloatingActionButton) findViewById(R.id.floatingactionbutton_bluetooth_tooglebluetooth);

        Toolbar toolbar;
        toolbar = (Toolbar) findViewById(R.id.toolbar_bluetooth);

        mRecyclerViewFoundDevices = (RecyclerView) findViewById(R.id.reciclerview_bluetooth);

        mFoundDevicesAdapter = new FoundDevicesAdapter(this);

        setUpRecyclerView();
        setUpToolbar(toolbar);
        mFloatingActionButtonToogleBluetooth.setOnClickListener(this);

// Ask for location permission if not already allowed
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        mPresenter = new BluetoothPresenter(this);
        mBroadcastDeviceFound = new BroadcastDeviceFound(mPresenter);
        mBroadcastStateBluetooth = new BroadcastStateBluetooth(mPresenter);

        IntentFilter intentBluetoothState = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBroadcastStateBluetooth, intentBluetoothState);

        IntentFilter intentDeviceFound = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mBroadcastDeviceFound, intentDeviceFound);

        mPresenter.onCreate();
    }

    private void setUpToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.bluetooth_toolbartitle);
        getSupportActionBar().setIcon(R.drawable.ic_main_bluetooth);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewFoundDevices.setLayoutManager(layoutManager);
        mRecyclerViewFoundDevices.setHasFixedSize(true);
        mRecyclerViewFoundDevices.setAdapter(mFoundDevicesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter discoverDevicesIntent = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mBroadcastDeviceFound, discoverDevicesIntent);
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(mBroadcastDeviceFound);
        unregisterReceiver(mBroadcastStateBluetooth);
    }

    @Override
    public void onClick(View v) {
        mViewForSnackbar = v;
        switch (v.getId()) {
            case R.id.floatingactionbutton_bluetooth_tooglebluetooth:
                mPresenter.onToogleButtonClicked();
        }
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {
        mPresenter.onListItemClicked(clickedItemIndex);
    }

    @Override
    public void showBluetoothRequest() {
        Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivity(enableBTIntent);
    }

    @Override
    public void showMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.floatingactionbutton_bluetooth_tooglebluetooth), message, Snackbar.LENGTH_LONG);
        snackbar.show();
        //android.R.id.content);
    }

    @Override
    public void setUpAdapter(List<String> devicesNames) {
        mFoundDevicesAdapter.updateData(devicesNames);
    }

//    @Override
//    public void returnDevice(Hc05 device) {
//        Intent intent;
//        intent = new Intent();
//        intent.putExtra(IntentExtras.FOUND_DEVICE, device);
//        setResult(1, intent);
//        finish();
//    }

    @Override
    public void goToMainActivity(String adressDevice) {
        Intent intent;
        intent = new Intent();
        intent.putExtra(IntentExtras.FOUND_DEVICE, adressDevice);
        setResult(1, intent);
        finish();
    }
}
