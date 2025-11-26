package org.aston.app.random;

import java.util.Random;

public class DataGeneration {

    static Random meaning = new Random();

    static String randomModel(GetDataCar getDataCar) {

        int countElements = CarModels.values().length;
        int numder = meaning.nextInt(countElements);
        String modelName = CarModels.models(numder);

        if (numder == 0) {
            int random = meaning.nextInt(getDataCar.AUDI.length);
            modelName = modelName + getDataCar.AUDI[random];
        }

        if (numder == 1) {
            int random = meaning.nextInt(getDataCar.TOYOTA.length);
            modelName = modelName + getDataCar.TOYOTA[random];
        }

        if (numder == 2) {
            int random = meaning.nextInt(getDataCar.FORD.length);
            modelName = modelName + getDataCar.FORD[random];
        }

        if (numder == 3) {
            int random = meaning.nextInt(getDataCar.BMW.length);
            modelName = modelName + getDataCar.BMW[random];
        }

        return modelName;
    }

    static int randomPower(int minPower, int maxPower) {
        return meaning.nextInt(maxPower - minPower) + maxPower;
    }

    static int randomYear() {
        return 1990 + meaning.nextInt(35);
    }
}
