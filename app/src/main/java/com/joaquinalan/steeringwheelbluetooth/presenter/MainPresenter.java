package com.joaquinalan.steeringwheelbluetooth.presenter;

import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.Bluetooth;
import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.bluetoothconnection.BluetoothServiceListener;
import com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor.SteeringWheelListener;
import com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor.SteeringWheelSensor;
import com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor.SteeringWheelSensorImpl;
import com.joaquinalan.steeringwheelbluetooth.view.MessageConstants;
import com.joaquinalan.steeringwheelbluetooth.view.MvpMainView;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public class MainPresenter implements MvpMainPresenter, SteeringWheelListener, BluetoothServiceListener {
    private final SteeringWheelSensor mSteeringWheelSensor;
    private MvpMainView mView;
    private Bluetooth mBluetooth;
    private boolean mBluetoothConnected = false;

    public MainPresenter(MvpMainView view) {
        mSteeringWheelSensor = new SteeringWheelSensorImpl(this, view);
        this.mView = view;
        mBluetooth = new Bluetooth();
    }

    @Override
    public void onBluetoothButtonClicked() {
        mView.startBluetoothActivity();
    }

    @Override
    public void onResume() {
        mSteeringWheelSensor.start();
    }

    @Override
    public void onPause() {
        mSteeringWheelSensor.stop();
    }

    @Override
    public void onDeviceChoosen(String deviceAdress) {
        mBluetooth.startConnection(deviceAdress, this);
        mBluetooth.setListener(this);
    }

    @Override
    public void onDeviceConnected() {
        mBluetoothConnected = true;
        mView.showMessage(MessageConstants.DEVICE_CONNECTED);
    }

    @Override
    public void onDeviceDisconnected() {
        mBluetoothConnected = false;
        mView.showMessage(MessageConstants.DEVICE_DISCONNECTED);
    }

    @Override
    public void onSteeringWheelChanged(int steeringWheelState) {
        String state = String.valueOf(steeringWheelState);
        mView.showSteeringWheelState(state);
        if (mBluetoothConnected) {
            writeData((byte) steeringWheelState);
        }
    }

    private void writeData(byte steeringWheelState) {
        byte[] dataInBytesstate = new byte[1];
        dataInBytesstate[0] = steeringWheelState;
        mBluetooth.write(dataInBytesstate);
    }

    @Override
    public void onConnectedSocket() {
        mBluetoothConnected = true;
        mView.showMessage(MessageConstants.BLUETOOTH_CONNECTED);
    }

    @Override
    public void onConnectionFailed() {
        mView.showMessage(MessageConstants.CONNECTION_FAILED);
    }
}
