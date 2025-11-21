package org.aston.app.random;

import org.aston.app.model.Car;

public class GetLowpowerCar implements RandomGeneration {

    private final int MIN_POWER = 87;
    private final int MAX_POWER = 110;

    private String model;
    private int power;
    private int year;

    public GetLowpowerCar() {
        this.model = CarModels.AUDI.toString(); //randomModel(MIN_POWER, MAX_POWER);
        this.year = 2025;//randomYear();
        this.power = 100;//randomPower(MIN_POWER, MAX_POWER);
    }

    private String randomModel(int minPower, int maxPower) {
        return null;
    }

    @Override
    public Car randomCar() {
        return new Car.Builder().setModel(model).setPower(power).setYear(year).build();
    }
}
