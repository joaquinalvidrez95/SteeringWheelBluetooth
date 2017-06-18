package com.joaquinalan.steeringwheelbluetooth.model;

/**
 * Created by joaquinalan on 17/06/2017.
 */

public interface ObservableSensor {
    void registerObserver(SensorObserver sensorObserver);

    void removeObserver(SensorObserver sensorObserver);

    void notifyObservers();

}
