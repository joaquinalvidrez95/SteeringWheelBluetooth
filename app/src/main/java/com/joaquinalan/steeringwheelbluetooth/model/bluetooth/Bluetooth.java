package com.joaquinalan.steeringwheelbluetooth.model.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.bluetoothconnection.BluetoothService;
import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.bluetoothconnection.BluetoothServiceListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class Bluetooth implements BluetoothServiceListener {
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothDevicesCollection mDiscoveredDevices;
    private BluetoothService mBluetoothService;
    private BluetoothServiceListener mBluetoothServiceListener;

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
        mBluetoothService = new BluetoothService(this);
        mBluetoothService.startClientConnection(mDiscoveredDevices.getDevice(clickedItemIndex));
    }

    public void startServerConnection() {
        mBluetoothService = new BluetoothService(this);
    }

    public List<String> getDevicesNames() {
        List<String> devicesNames = new ArrayList();
        Iterator<BluetoothDevice> iterator;
        iterator = mDiscoveredDevices.getIterator();
        while (iterator.hasNext()) {
            BluetoothDevice bluetoothDevice;
            bluetoothDevice = iterator.next();
            String deviceName;
            deviceName = bluetoothDevice.getName();
            devicesNames.add(deviceName);
        }
        return devicesNames;
    }

    public void disableBluetooth() {
        mBluetoothAdapter.disable();
    }

    public Hc05 getDevice(int clickedItemIndex) {
        Hc05 device;
        device = new Hc05(mDiscoveredDevices.getDevice(clickedItemIndex));
        return device;
    }

    public String getAdressDevice(int clickedItemIndex) {
        String deviceAdress;
        deviceAdress = mDiscoveredDevices.getDevice(clickedItemIndex).getAddress();
        return deviceAdress;
    }

    public void enable() {
        mBluetoothAdapter.enable();
    }

    public void startConnection(String deviceAdress, BluetoothServiceListener bluetoothServiceListener) {
        mBluetoothServiceListener = bluetoothServiceListener;
        mBluetoothService = new BluetoothService(this);
        for (BluetoothDevice bluetoothDevice : mBluetoothAdapter.getBondedDevices()) {
            if (bluetoothDevice.getAddress().equals(deviceAdress)) {
                mBluetoothService.startClientConnection(bluetoothDevice);
            }
        }
    }

    @Override
    public void onConnectedSocket() {
        mBluetoothServiceListener.onConnectedSocket();
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
