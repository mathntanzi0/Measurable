package com.netanzi.measurable;

import java.text.DecimalFormat;

public class Utilities {
    public static String roundToTwoDecimalPlaces(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(value);
    }
}
