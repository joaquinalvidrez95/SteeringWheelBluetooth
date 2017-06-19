package com.joaquinalan.steeringwheelbluetooth.presenter;

import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.Bluetooth;
import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.Hc05;
import com.joaquinalan.steeringwheelbluetooth.view.BluetoothView;
import com.joaquinalan.steeringwheelbluetooth.view.MessageConstants;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public class BluetoothPresenter implements MvpBluetoothPresenter {
    private BluetoothView mView;
    private Bluetooth mBluetooth;
//    private boolean mBluetoothTurningOn;

    public BluetoothPresenter(BluetoothView bluetoothView) {
        this.mView = bluetoothView;
        mBluetooth = new Bluetooth();
    }

    @Override
    public void onToogleButtonClicked() {
        if (!mBluetooth.isBluetoothEnabled()) {
            if (!mBluetooth.hasBluetooth()) {
                mView.showMessage(MessageConstants.NOT_HAVING_BLUETOOTH);
            }
            mBluetooth.enable();
            //mView.showBluetoothRequest();
        } else {
            mBluetooth.disableBluetooth();
            mView.showMessage(MessageConstants.BLUETOOTH_DISABLED);
        }
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onCreate() {
        if (mBluetooth.isBluetoothEnabled()) {
            mBluetooth.startDiscovery();
            mView.showMessage(MessageConstants.CHOOSE_A_DEVICE);
        } else {
            mBluetooth.enable();
            //mView.showMessage(MessageConstants.ENABLE_BLUETOOTH);
            //mView.showBluetoothRequest();
        }
    }

    @Override
    public void onBluetoothRequestAccepted() {
        mView.showMessage(MessageConstants.BLUETOOTH_ENEABLED);
        mBluetooth.startDiscovery();
    }

    @Override
    public void onBluetoothDeviceFound(Hc05 device) {
        mBluetooth.addFoundDevice(device.getBluetoothDevice());
        mView.setUpAdapter(mBluetooth.getDevicesNames());
    }

    @Override
    public void onListItemClicked(int clickedItemIndex) {
        //mBluetooth.startConnection(clickedItemIndex);
        mView.goToMainActivity(mBluetooth.getAdressDevice(clickedItemIndex));
    }

    @Override
    public void onBluetoothTurningOn() {
        //mBluetoothTurningOn = true;
        mView.showMessage("Bluetooth turning on");
    }
}
