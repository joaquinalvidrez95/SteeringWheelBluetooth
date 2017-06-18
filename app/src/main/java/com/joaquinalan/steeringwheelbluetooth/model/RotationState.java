package com.joaquinalan.steeringwheelbluetooth.model;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class RotationState implements MotionState {
    private final double INITIAL_RIGHT = -90.00;
    private final double FINAL_RIGHT = -30.00;

    private final double INITIAL_NOT_ROTATE = -30.00;
    private final double FINAL_NOT_ROTATE = 30.00;

    private final double INITIAL_LEFT = 30.00;
    private final double FINAL_LEFT = 90.00;

    private int mRotationState;
    private double mAxisXAngle;

    public RotationState(double axisXAngle) {
        mAxisXAngle = axisXAngle;
    }

    @Override
    public int getMotionState() {
        if (RangeTester.isRange(mAxisXAngle, INITIAL_RIGHT, FINAL_RIGHT)) {
            mRotationState = BACKWARD;
        } else if (RangeTester.isRange(mAxisXAngle, INITIAL_NOT_ROTATE, FINAL_NOT_ROTATE)) {
            mRotationState = NOT_TRANSLATE;
        } else if (RangeTester.isRange(mAxisXAngle, INITIAL_LEFT, FINAL_LEFT)) {
            mRotationState = FORWARD;
        }
        return mRotationState;
    }
}
