package com.joaquinalan.steeringwheelbluetooth.view.activity;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.joaquinalan.steeringwheelbluetooth.R;
import com.joaquinalan.steeringwheelbluetooth.broadcastreceiver.BroadcastConnectionState;
import com.joaquinalan.steeringwheelbluetooth.presenter.MainPresenter;
import com.joaquinalan.steeringwheelbluetooth.presenter.MvpMainPresenter;
import com.joaquinalan.steeringwheelbluetooth.view.MessageConstants;
import com.joaquinalan.steeringwheelbluetooth.view.MvpMainView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, MvpMainView {
    private static final int REQUEST_DEVICE_CODE = 0;
    private FloatingActionButton mFloatingActionButtonSensor;
    private MvpMainPresenter mPresenter;
    private TextView mTextViewState;
    private BroadcastReceiver mBroadcastConnectionState;
//    private SteeringWheelSensor mSteeringWheelSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new MainPresenter(this);
        FloatingActionButton mFloatingActionButtonBluetooth;
        mFloatingActionButtonBluetooth = (FloatingActionButton) findViewById(R.id.floatingactionbutton_main_bluetooth);
        mFloatingActionButtonSensor = (FloatingActionButton) findViewById(R.id.floatingactionbutton_main_sensor);

        mTextViewState = (TextView) findViewById(R.id.textview_main_steeringwheelstate);

        mFloatingActionButtonBluetooth.setOnClickListener(this);
        mFloatingActionButtonSensor.setOnClickListener(this);

        mBroadcastConnectionState = new BroadcastConnectionState(mPresenter);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);
        registerReceiver(mBroadcastConnectionState, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastConnectionState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //data.getSerializableExtra(IntentExtras.FOUND_DEVICE)
        String deviceAdress = data.getStringExtra(IntentExtras.FOUND_DEVICE);
        mPresenter.onDeviceChoosen(deviceAdress);
        //Toast.makeText(this, deviceAdress, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingactionbutton_main_bluetooth:
                mPresenter.onBluetoothButtonClicked();
                break;
            case R.id.floatingactionbutton_main_sensor:
                //v.setBackgroundResource(R.drawable.ic_main_pause);
                mPresenter.onSensorButtonClicked();
                break;
        }
    }

    @Override
    public void startBluetoothActivity() {
        Intent bluetoothIntent = new Intent(this, BluetoothActivity.class);
        startActivityForResult(bluetoothIntent, REQUEST_DEVICE_CODE);
    }

    @Override
    public void showMessage(String message) {
        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.floatingactionbutton_main_bluetooth), message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    @Override
    public void showSteeringWheelState(String state) {
        mTextViewState.setText(state);
    }

    @Override
    public void turnSensorButtonOn() {
        Snackbar.make(findViewById(R.id.floatingactionbutton_main_sensor),
                MessageConstants.STERERING_WHEEL_PAUSED, Snackbar.LENGTH_LONG).show();
        mFloatingActionButtonSensor.setBackgroundResource(R.drawable.ic_main_play);
    }

    @Override
    public void turnSensorButtonOff() {
        Snackbar.make(findViewById(R.id.floatingactionbutton_main_sensor),
                MessageConstants.STERERING_WHEEL_STARTED, Snackbar.LENGTH_LONG).show();
        mFloatingActionButtonSensor.setBackgroundResource(R.drawable.ic_main_pause);
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

//    @Override
//    public void onSteeringWheelChanged(int steeringWheelState) {
//        mPresenter.onSteeringWheelChanged(steeringWheelState);
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.item_optionsmenu_bluetooth:
//                Intent bluetoothIntent = new Intent(this, BluetoothActivity.class);
//                startActivity(bluetoothIntent);
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
