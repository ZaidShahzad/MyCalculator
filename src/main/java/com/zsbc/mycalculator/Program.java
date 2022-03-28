package com.zsbc.mycalculator;

import com.zsbc.mycalculator.struct.Calculator;
import com.zsbc.zsbcutils.Debug;

public class Program {

    /*
    Project Description:
    A very simple calculator application written in Java.
     */

    private static Debug debug;

    public static void main(String[] args) {
        debug = new Debug(false);
        new Calculator();
    }

    public static Debug getDebug() {
        return debug;
    }
}
