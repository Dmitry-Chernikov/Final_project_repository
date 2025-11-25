package org.aston.app.random;

public enum CarModels {
    AUDI, TOYOTA, FORD, BMW;

    public static String models(int number) {
        CarModels[] models = CarModels.values();
        return models[number].toString();
    }
}
