package com.joaquinalan.steeringwheelbluetooth.model;

/**
 * Created by joaquinalan on 17/06/2017.
 */

public interface SensorObserver {
    void onAngleChanged(float[] orientationAngles);
}
