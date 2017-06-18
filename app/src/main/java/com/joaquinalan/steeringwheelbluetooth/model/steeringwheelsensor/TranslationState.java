package com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class TranslationState implements MotionState {
    private int mTranslationState;

    private final double INITIAL_BACKWARD = -180.00;
    private final double FINAL_BACKWARD = -90.00;

    private final double INITIAL_NOT_TRANSLATE = -90.00;
    private final double FINAL_NOT_TRANSLATE = 10.00;

    private final double INITIAL_FORWARD = 10.00;
    private final double FINAL_FORWARD = 180.00;

    private double mAxisYAngle;

    public TranslationState(double axisYAngle) {
        this.mAxisYAngle = axisYAngle;
    }

    @Override
    public int getMotionState() {
        if (RangeTester.isRange(mAxisYAngle, INITIAL_BACKWARD, FINAL_BACKWARD)) {
            mTranslationState = BACKWARD;
        } else if (RangeTester.isRange(mAxisYAngle, INITIAL_NOT_TRANSLATE, FINAL_NOT_TRANSLATE)) {
            mTranslationState = NOT_TRANSLATE;
        } else if (RangeTester.isRange(mAxisYAngle, INITIAL_FORWARD, FINAL_FORWARD)) {
            mTranslationState = FORWARD;
        }
        return mTranslationState;
    }
}
