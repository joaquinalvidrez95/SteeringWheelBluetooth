package com.joaquinalan.steeringwheelbluetooth.model;

/**
 * Created by joaquinalan on 16/06/2017.
 */

public class RangeTester {
    public static boolean isRange(double numberToTest, double minimum, double maximum) {
        return (numberToTest <= maximum && numberToTest >= minimum);
    }
}
