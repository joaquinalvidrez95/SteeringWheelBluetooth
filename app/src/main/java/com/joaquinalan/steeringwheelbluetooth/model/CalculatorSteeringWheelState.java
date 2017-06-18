package com.joaquinalan.steeringwheelbluetooth.model;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class CalculatorSteeringWheelState {
    private int steeringWheelState;
    private double axisXAngle;
    private double axisYAngle;

    static final double CONVERSION_RADIANS_TO_DEGREES = 180.00 / Math.PI;

    private static final int ROBOT_BACKWARD_RIGHT = 1;
    private static final int ROBOT_BACKWARD = 2;
    private static final int ROBOT_BACKWARD_LEFT = 3;
    private static final int ROBOT_TURN_RIGHT = 4;
    private static final int ROBOT_STOP = 5;
    private static final int ROBOT_TURN_LEFT = 6;
    private static final int ROBOT_FORWARD_RIGHT = 7;
    private static final int ROBOT_FORWARD = 8;
    private static final int ROBOT_FORWARD_LEFT = 9;

    public CalculatorSteeringWheelState(float[] mOrientationAngles) {
        axisXAngle = mOrientationAngles[1] * CONVERSION_RADIANS_TO_DEGREES;
        axisYAngle = mOrientationAngles[2] * CONVERSION_RADIANS_TO_DEGREES;
        calculateStates();
    }

    private void calculateStates() {
        MotionState translationState = new TranslationState(axisYAngle);
        MotionState rotationState = new RotationState(axisXAngle);
        if (translationState.getMotionState() == MotionState.BACKWARD && rotationState.getMotionState() == MotionState.TURN_RIGHT) {
            steeringWheelState = ROBOT_BACKWARD_RIGHT;
        } else if (translationState.getMotionState() == MotionState.BACKWARD && rotationState.getMotionState() == MotionState.NOT_ROTATE) {
            steeringWheelState = ROBOT_BACKWARD;
        } else if (translationState.getMotionState() == MotionState.BACKWARD && rotationState.getMotionState() == MotionState.TURN_LEFT) {
            steeringWheelState = ROBOT_BACKWARD_LEFT;
        } else if (translationState.getMotionState() == MotionState.NOT_TRANSLATE && rotationState.getMotionState() == MotionState.TURN_RIGHT) {
            steeringWheelState = ROBOT_TURN_RIGHT;
        } else if (translationState.getMotionState() == MotionState.NOT_TRANSLATE && rotationState.getMotionState() == MotionState.NOT_ROTATE) {
            steeringWheelState = ROBOT_STOP;
        } else if (translationState.getMotionState() == MotionState.NOT_TRANSLATE && rotationState.getMotionState() == MotionState.TURN_LEFT) {
            steeringWheelState = ROBOT_TURN_LEFT;
        } else if (translationState.getMotionState() == MotionState.FORWARD && rotationState.getMotionState() == MotionState.TURN_RIGHT) {
            steeringWheelState = ROBOT_FORWARD_RIGHT;
        } else if (translationState.getMotionState() == MotionState.FORWARD && rotationState.getMotionState() == MotionState.NOT_ROTATE) {
            steeringWheelState = ROBOT_FORWARD;
        } else if (translationState.getMotionState() == MotionState.FORWARD && rotationState.getMotionState() == MotionState.TURN_LEFT) {
            steeringWheelState = ROBOT_FORWARD_LEFT;
        }
    }

    public static int calculateSteeringWheelState(float[] orientationAngles) {
        double axisXAngle = orientationAngles[1] * CONVERSION_RADIANS_TO_DEGREES;
        double axisYAngle = orientationAngles[2] * CONVERSION_RADIANS_TO_DEGREES;
        MotionState translationState = new TranslationState(axisYAngle);
        MotionState rotationState = new RotationState(axisXAngle);

        int steeringWheelState = ROBOT_STOP;
        if (translationState.getMotionState() == MotionState.BACKWARD && rotationState.getMotionState() == MotionState.TURN_RIGHT) {
            steeringWheelState = ROBOT_BACKWARD_RIGHT;
        } else if (translationState.getMotionState() == MotionState.BACKWARD && rotationState.getMotionState() == MotionState.NOT_ROTATE) {
            steeringWheelState = ROBOT_BACKWARD;
        } else if (translationState.getMotionState() == MotionState.BACKWARD && rotationState.getMotionState() == MotionState.TURN_LEFT) {
            steeringWheelState = ROBOT_BACKWARD_LEFT;
        } else if (translationState.getMotionState() == MotionState.NOT_TRANSLATE && rotationState.getMotionState() == MotionState.TURN_RIGHT) {
            steeringWheelState = ROBOT_TURN_RIGHT;
        } else if (translationState.getMotionState() == MotionState.NOT_TRANSLATE && rotationState.getMotionState() == MotionState.NOT_ROTATE) {
            steeringWheelState = ROBOT_STOP;
        } else if (translationState.getMotionState() == MotionState.NOT_TRANSLATE && rotationState.getMotionState() == MotionState.TURN_LEFT) {
            steeringWheelState = ROBOT_TURN_LEFT;
        } else if (translationState.getMotionState() == MotionState.FORWARD && rotationState.getMotionState() == MotionState.TURN_RIGHT) {
            steeringWheelState = ROBOT_FORWARD_RIGHT;
        } else if (translationState.getMotionState() == MotionState.FORWARD && rotationState.getMotionState() == MotionState.NOT_ROTATE) {
            steeringWheelState = ROBOT_FORWARD;
        } else if (translationState.getMotionState() == MotionState.FORWARD && rotationState.getMotionState() == MotionState.TURN_LEFT) {
            steeringWheelState = ROBOT_FORWARD_LEFT;
        }
        return steeringWheelState;
    }
}
