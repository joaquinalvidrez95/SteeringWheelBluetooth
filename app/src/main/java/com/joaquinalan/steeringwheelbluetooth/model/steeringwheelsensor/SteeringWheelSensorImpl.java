package com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.joaquinalan.steeringwheelbluetooth.view.Contextable;

/**
 * Created by joaquinalan on 15/06/2017.
 */

public class SteeringWheelSensorImpl implements SensorEventListener, SteeringWheelSensor {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mMagnetometer;

    private final float[] mAccelerometerReading = new float[3];
    private final float[] mMagnetometerReading = new float[3];

    private final float[] mRotationMatrix = new float[9];
    private final float[] mOrientationAngles = new float[3];

    SteeringWheelListener mSteeringWheelListener;

    private int mSteeringWheelState;
    private boolean mState;
    private final boolean ON = true;
    private final boolean OFF = false;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     * <p>
     * Used to name the worker thread, important only for debugging.
     */
    public SteeringWheelSensorImpl(SteeringWheelListener steeringWheelListener, Contextable contextable) {
        mSteeringWheelListener = steeringWheelListener;
        mSensorManager = (SensorManager) contextable.getContext().getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == mAccelerometer) {
            System.arraycopy(event.values, 0, mAccelerometerReading,
                    0, mAccelerometerReading.length);
        } else if (event.sensor == mMagnetometer) {
            System.arraycopy(event.values, 0, mMagnetometerReading,
                    0, mMagnetometerReading.length);
        }

        updateOrientationAngles();
        mSteeringWheelState = CalculatorSteeringWheelState.calculateSteeringWheelState(mOrientationAngles);
        mSteeringWheelListener.onSteeringWheelChanged(mSteeringWheelState);
    }

    // Compute the three orientation angles based on the most recent readings from
    // the device's accelerometer and magnetometer.
    private void updateOrientationAngles() {
        // Update rotation matrix, which is needed to update orientation angles.
        SensorManager.getRotationMatrix(mRotationMatrix, null,
                mAccelerometerReading, mMagnetometerReading);

        // "mRotationMatrix" now has up-to-date information.

        SensorManager.getOrientation(mRotationMatrix, mOrientationAngles);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public int getSteeringWheelState() {
        return mSteeringWheelState;
    }

    @Override
    public void stop() {
        mSensorManager.unregisterListener(this);
        mState = OFF;
    }

    @Override
    public void start() {
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mMagnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        mState = ON;
    }

    @Override
    public boolean isEnable() {
        return mState;
    }
}
