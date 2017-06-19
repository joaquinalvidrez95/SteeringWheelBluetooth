package com.joaquinalan.steeringwheelbluetooth.broadcastreceiver;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.joaquinalan.steeringwheelbluetooth.presenter.MvpMainPresenter;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public class BroadcastConnectionState extends BroadcastReceiver {
    MvpMainPresenter mPresenter;

    public BroadcastConnectionState(MvpMainPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (BluetoothDevice.ACTION_ACL_CONNECTED.equals(action)) {
            mPresenter.onDeviceConnected();
        } else if (BluetoothDevice.ACTION_ACL_DISCONNECTED.equals(action)) {
            mPresenter.onDeviceDisconnected();
        }

    }
}
