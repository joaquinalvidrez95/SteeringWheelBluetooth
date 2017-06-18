package com.joaquinalan.steeringwheelbluetooth.view;

import java.util.List;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public interface BluetoothView {
    void showBluetoothRequest();

    void showMessage(String message);

    void setUpAdapter(List<String> devicesNames);

    //void returnDevice(Hc05 device);

    void goToMainActivity(String adressDevice);
}
