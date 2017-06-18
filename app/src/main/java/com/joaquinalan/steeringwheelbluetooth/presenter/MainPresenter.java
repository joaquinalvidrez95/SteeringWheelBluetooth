package com.joaquinalan.steeringwheelbluetooth.presenter;

import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.Bluetooth;
import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.bluetoothconnection.BluetoothServiceListener;
import com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor.SteeringWheelListener;
import com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor.SteeringWheelSensor;
import com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor.SteeringWheelSensorImpl;
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
    }

    @Override
    public void onSteeringWheelChanged(int steeringWheelState) {
        String state = String.valueOf(steeringWheelState);
        mView.showSteeringWheelState(state);
        mBluetooth.write()
    }

    @Override
    public void onConnectedSocket() {
        mBluetoothConnected = true;
    }
}
