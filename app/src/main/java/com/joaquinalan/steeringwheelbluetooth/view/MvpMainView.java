package com.joaquinalan.steeringwheelbluetooth.view;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public interface MvpMainView extends Contextable {
    void startBluetoothActivity();

    void showSteeringWheelState(String state);
}
