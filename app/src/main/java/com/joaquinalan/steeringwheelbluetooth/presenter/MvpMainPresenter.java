package com.joaquinalan.steeringwheelbluetooth.presenter;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public interface MvpMainPresenter {

    void onBluetoothButtonClicked();

    void onResume();

    void onPause();

    void onDeviceChoosen(String deviceAdress);

    void onDeviceConnected();

    void onDeviceDisconnected();
}
