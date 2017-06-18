package com.joaquinalan.steeringwheelbluetooth.model.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.bluetoothconnection.BluetoothService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class Bluetooth {
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevicesCollection mDiscoveredDevices;
    private BluetoothService mBluetoothService;

    /**
     * The Handler that gets information back from the BluetoothChatService
     */
    public Bluetooth() {
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mDiscoveredDevices = new BluetoothDevicesCollection();
    }

    public void startDiscovery() {
        mBluetoothAdapter.startDiscovery();
    }

    public void stopDiscovery() {
        mBluetoothAdapter.cancelDiscovery();
    }

    public void addFoundDevice(BluetoothDevice device) {
        mDiscoveredDevices.addBluetoothDevices(device);
    }

//    public boolean hasDevice(BluetoothDevice device) {
//        return mDiscoveredDevices.contains(device);
//    }

    public boolean hasBluetooth() {
        return mBluetoothAdapter != null;
    }

    public boolean isBluetoothEnabled() {
        return mBluetoothAdapter.isEnabled();
    }

    public void startConnection(int clickedItemIndex) {
        mBluetoothService = new BluetoothService();
        mBluetoothService.startClientConnection(mDiscoveredDevices.getDevice(clickedItemIndex));
    }

    public void startServerConnection() {
        mBluetoothService = new BluetoothService();
    }

    public List<String> getDevicesNames() {
        List<String> devicesNames = new ArrayList();
        Iterator<BluetoothDevice> iterator = mDiscoveredDevices.getIterator();
        while (iterator.hasNext()) {
            BluetoothDevice bluetoothDevice = iterator.next();
            String deviceName = bluetoothDevice.getName();
            devicesNames.add(deviceName);
        }
        return devicesNames;
    }

//    @Override
//    public void onSensorChanged(float[] mOrientationAngles) {
//        int angle = CalculatorSteeringWheelState.calculateSteeringWheelState(mOrientationAngles);
//        byte[] bytes;
//        bytes = new byte[1];
//        bytes[1] = (byte) angle;
//        mBluetoothService.write(bytes);
//    }
}
