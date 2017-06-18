package com.joaquinalan.steeringwheelbluetooth.model.bluetooth.bluetoothconnection;

import android.bluetooth.BluetoothSocket;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public interface BluetoothConnection {
    void onConnectedSocket(BluetoothSocket bluetoothSocket);
}
