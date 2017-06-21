package com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class Robot {
    private static final int ROBOT_BACKWARD_RIGHT = 1;
    private static final int ROBOT_BACKWARD = 2;
    private static final int ROBOT_BACKWARD_LEFT = 3;
    private static final int ROBOT_TURN_RIGHT = 4;
    private static final int ROBOT_STOP = 5;
    private static final int ROBOT_TURN_LEFT = 6;
    private static final int ROBOT_FORWARD_RIGHT = 7;
    private static final int ROBOT_FORWARD = 8;
    private static final int ROBOT_FORWARD_LEFT = 9;
    private static String mRobotState;
    private static int mSteeringWheelState;

    public Robot(int steeringWheelState) {
        mSteeringWheelState = steeringWheelState;
    }

    static public String getRobotState(int steeringWheelState) {
        switch (steeringWheelState) {
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
                mRobotState = "Robot turning right";
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
