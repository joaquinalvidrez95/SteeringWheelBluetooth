package com.joaquinalan.steeringwheelbluetooth.model.bluetooth;

import android.bluetooth.BluetoothDevice;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public class Hc05 {
    private BluetoothDevice mBluetoothDevice;

    public Hc05(BluetoothDevice bluetoothDevice) {
        this.mBluetoothDevice = bluetoothDevice;
    }

    public BluetoothDevice getBluetoothDevice() {
        return mBluetoothDevice;
    }
}
