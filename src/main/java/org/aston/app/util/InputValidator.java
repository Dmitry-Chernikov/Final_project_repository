package org.aston.app.util;

import java.util.Arrays;

public class InputValidator {

    public static boolean isValidModel(String model) {
        return Arrays.asList(DataGenerator.MODELS).contains(model);
    }

    public static boolean isValidPower(int power) {
        return power >= 1 && power <= 2000;
    }

    public static boolean isValidYear(int year) {
        return year >= 1885 && year <= 2025;
    }
}
