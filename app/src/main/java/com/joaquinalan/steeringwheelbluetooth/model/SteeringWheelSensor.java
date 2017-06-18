package com.joaquinalan.steeringwheelbluetooth.model;

/**
 * Created by joaquinalan on 18/06/2017.
 */

public interface SteeringWheelSensor {
    int getSteeringWheelState();

    void stop();

    void start();
}
