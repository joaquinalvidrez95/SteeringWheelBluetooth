package com.joaquinalan.steeringwheelbluetooth.broadcastreceiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.Hc05;
import com.joaquinalan.steeringwheelbluetooth.presenter.MvpBluetoothPresenter;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class BroadcastDeviceFound extends BroadcastReceiver {
    private MvpBluetoothPresenter mPresenter;

    public BroadcastDeviceFound(MvpBluetoothPresenter mainPresenter) {
        this.mPresenter = mainPresenter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // When discovery finds a device
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            // Get the BluetoothDevice object from the Intent
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            mPresenter.onBluetoothDeviceFound(new Hc05(device));
        }
    }
}
