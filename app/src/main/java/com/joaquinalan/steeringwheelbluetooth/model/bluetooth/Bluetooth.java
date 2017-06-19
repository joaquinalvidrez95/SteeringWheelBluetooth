package com.joaquinalan.steeringwheelbluetooth.model.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Handler;
import android.os.Message;

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

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            byte[] readBuf = (byte[]) msg.obj;
            int numberOfBytes = msg.arg1;

            // construct a string from the valid bytes in the buffer
            String readMessage = new String(readBuf, 0, numberOfBytes);
        }
    };

    /**
     * The Handler that gets information back from the BluetoothChatService
     */
    public Bluetooth() {
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mDiscoveredDevices = new BluetoothDevicesCollection();
    }

    public Bluetooth(BluetoothServiceListener bluetoothServiceListener) {
        this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mDiscoveredDevices = new BluetoothDevicesCollection();
        mBluetoothServiceListener = bluetoothServiceListener;
    }

    public void setListener(BluetoothServiceListener listener) {
        mBluetoothServiceListener = listener;
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
        //mBluetoothService = new BluetoothService2(this, mHandler);
        mBluetoothService = new BluetoothService(mHandler);
        mBluetoothService.startClient(mDiscoveredDevices.getDevice(clickedItemIndex));
    }

    public void startServerConnection() {
        mBluetoothService = new BluetoothService(mHandler);
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
        //mBluetoothServiceListener = bluetoothServiceListener;
        //mBluetoothService = new BluetoothService2(this, mHandler);
        mBluetoothService = new BluetoothService(mHandler);
        for (BluetoothDevice bluetoothDevice : mBluetoothAdapter.getBondedDevices()) {
            if (bluetoothDevice.getAddress().equals(deviceAdress)) {
                mBluetoothService.startClient(bluetoothDevice);
            }
        }
    }

    @Override
    public void onConnectedSocket() {
        if (!(mBluetoothServiceListener == null)) {
            mBluetoothServiceListener.onConnectedSocket();
        }
    }

    @Override
    public void onConnectionFailed() {
        //mBluetoothServiceListener.onConnectionFailed();
    }

    public void write(byte[] dataInBytes) {
        mBluetoothService.write(dataInBytes);
    }

}
