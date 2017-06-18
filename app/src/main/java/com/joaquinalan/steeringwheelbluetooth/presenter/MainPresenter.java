package com.joaquinalan.steeringwheelbluetooth.presenter;

import com.joaquinalan.steeringwheelbluetooth.view.MvpMainView;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public class MainPresenter implements MvpMainPresenter {
    private MvpMainView mView;

    public MainPresenter(MvpMainView view) {
        this.mView = view;
    }

    @Override
    public void onBluetoothButtonClicked() {
        mView.startBluetoothActivity();
    }

    @Override
    public void onSteeringWheelChanged(int steeringWheelState) {
        String state = String.valueOf(steeringWheelState);
        mView.showSteeringWheelState(state);
    }
}
