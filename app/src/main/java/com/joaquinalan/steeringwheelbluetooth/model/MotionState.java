package com.joaquinalan.steeringwheelbluetooth.model;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public interface MotionState {
    int getMotionState();

    int BACKWARD = 1;
    int NOT_TRANSLATE = 2;
    int FORWARD = 3;

    int TURN_RIGHT = 1;
    int NOT_ROTATE = 2;
    int TURN_LEFT = 3;
}
