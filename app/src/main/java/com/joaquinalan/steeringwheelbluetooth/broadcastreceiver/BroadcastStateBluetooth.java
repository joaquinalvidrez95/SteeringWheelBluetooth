package com.joaquinalan.steeringwheelbluetooth.broadcastreceiver;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.joaquinalan.steeringwheelbluetooth.presenter.MvpBluetoothPresenter;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class BroadcastStateBluetooth extends BroadcastReceiver {
    private MvpBluetoothPresenter mPresenter;

    public BroadcastStateBluetooth(MvpBluetoothPresenter presenter) {
        mPresenter = presenter;
    }
    // Create a BroadcastReceiver for ACTION_FOUND

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // When discovery finds a device
        if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {

            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                    //mPresenter.onBluetoothWasDisabled();
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    break;
                case BluetoothAdapter.STATE_ON:
                    mPresenter.onBluetoothRequestAccepted();
                    //mBluetooth.startServerConnection();
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    break;
            }
        }
    }
}
