package com.joaquinalan.steeringwheelbluetooth.presenter;

import com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor.SteeringWheelListener;
import com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor.SteeringWheelSensor;
import com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor.SteeringWheelSensorImpl;
import com.joaquinalan.steeringwheelbluetooth.view.MvpMainView;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public class MainPresenter implements MvpMainPresenter, SteeringWheelListener {
    private final SteeringWheelSensor mSteeringWheelSensor;
    private MvpMainView mView;

    public MainPresenter(MvpMainView view) {
        mSteeringWheelSensor = new SteeringWheelSensorImpl(this, view);
        this.mView = view;
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
    public void onSteeringWheelChanged(int steeringWheelState) {
        String state = String.valueOf(steeringWheelState);
        mView.showSteeringWheelState(state);
    }
}
