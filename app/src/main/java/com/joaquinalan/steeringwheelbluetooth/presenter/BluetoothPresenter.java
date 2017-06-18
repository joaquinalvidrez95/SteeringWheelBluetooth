package com.joaquinalan.steeringwheelbluetooth.presenter;

import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.Bluetooth;
import com.joaquinalan.steeringwheelbluetooth.view.BluetoothView;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public class BluetoothPresenter implements MvpBluetoothPresenter {
    private BluetoothView mView;
    private Bluetooth mBluetooth;

    public BluetoothPresenter(BluetoothView bluetoothView) {
        this.mView = bluetoothView;
        mBluetooth = new Bluetooth();
    }

    @Override
    public void onToogleButtonClicked() {
        if (mBluetooth.isBluetoothEnabled()) {

        }
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
}
