package com.joaquinalan.steeringwheelbluetooth.model.bluetooth;

import android.bluetooth.BluetoothDevice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class BluetoothDevicesCollection {
    private List<BluetoothDevice> mBluetoothDevices;

    public BluetoothDevicesCollection() {
        mBluetoothDevices = new ArrayList<>();
    }

    public void addBluetoothDevices(BluetoothDevice bluetoothDevice) {
        if (!mBluetoothDevices.contains(bluetoothDevice)) {
            mBluetoothDevices.add(bluetoothDevice);
        }
    }

    public BluetoothDevice getDevice(int position) {
        return mBluetoothDevices.get(position);
    }

    Iterator<BluetoothDevice> getIterator() {
        return mBluetoothDevices.iterator();
    }

}
