package com.joaquinalan.steeringwheelbluetooth.presenter;

import com.joaquinalan.steeringwheelbluetooth.model.bluetooth.Hc05;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public interface MvpBluetoothPresenter {
    void onToogleButtonClicked();

    void onResume();

    void onPause();

    void onCreate();

    void onBluetoothRequestAccepted();

    void onBluetoothDeviceFound(Hc05 device);
}
