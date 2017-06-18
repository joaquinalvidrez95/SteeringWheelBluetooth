package com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class Robot {
    private final int ROBOT_BACKWARD_RIGHT = 1;
    private final int ROBOT_BACKWARD = 2;
    private final int ROBOT_BACKWARD_LEFT = 3;
    private final int ROBOT_TURN_RIGHT = 4;
    private final int ROBOT_STOP = 5;
    private final int ROBOT_TURN_LEFT = 6;
    private final int ROBOT_FORWARD_RIGHT = 7;
    private final int ROBOT_FORWARD = 8;
    private final int ROBOT_FORWARD_LEFT = 9;
    private String mRobotState;
    private int mSteeringWheelState;

    public Robot(int steeringWheelState) {
        mSteeringWheelState = steeringWheelState;
    }

    public String getRobotState() {
        switch (mSteeringWheelState) {
            case ROBOT_BACKWARD_RIGHT:
                mRobotState = "Robot moving backward and right";
                break;
            case ROBOT_BACKWARD:
                mRobotState = "Robot moving backward";
                break;
            case ROBOT_BACKWARD_LEFT:
                mRobotState = "Robot moving backward and left";
                break;
            case ROBOT_TURN_RIGHT:
                mRobotState = "Robot moving turning right";
                break;
            case ROBOT_STOP:
                mRobotState = "Robot stopped";
                break;
            case ROBOT_TURN_LEFT:
                mRobotState = "Robot turning left";
                break;
            case ROBOT_FORWARD_RIGHT:
                mRobotState = "Robot moving forward and right";
                break;
            case ROBOT_FORWARD:
                mRobotState = "Robot moving forward";
                break;
            case ROBOT_FORWARD_LEFT:
                mRobotState = "Robot moving forward and left";
                break;
        }
        return mRobotState;
    }
}
