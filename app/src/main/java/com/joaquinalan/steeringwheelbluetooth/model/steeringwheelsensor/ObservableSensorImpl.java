package com.joaquinalan.steeringwheelbluetooth.model.steeringwheelsensor;

/**
 * Created by joaquinalan on 17/06/2017.
 */

public class ObservableSensorImpl implements ObservableSensor {
    //private Handler mAngleSensorHandler;
    private float[] mOrientationAngles;
    //private List<SensorObserver> mSensorObservers;

    public ObservableSensorImpl() {
        //mAngleSensorHandler = new AngleSensorHandler(this);
        //mSensorObservers = new ArrayList<>();
    }

//    private final Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            mOrientationAngles = (float[]) msg.obj;
//            notifyObservers();
//        }
//    };

    @Override
    public void registerObserver(SensorObserver sensorObserver) {
        //mSensorObservers.add(sensorObserver);
    }

    @Override
    public void removeObserver(SensorObserver sensorObserver) {
        //mSensorObservers.remove(sensorObserver);
    }

    @Override
    public void notifyObservers() {
//        for (SensorObserver sensorObserver : mSensorObservers
//                ) {
//            sensorObserver.onAngleChanged(mOrientationAngles);
//        }
    }

    public void setAngles(float[] angles) {
        mOrientationAngles = angles;
        notifyObservers();
    }
}
