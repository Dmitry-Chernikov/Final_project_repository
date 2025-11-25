package org.aston.app.random;

import org.aston.app.model.Car;


public class GetMidCar implements RandomGeneration {
    private final int MIN_POWER = 110;
    private final int MAX_POWER = 150;

    class MidCarData extends GetDataCar {
        MidCarData() {
            AUDI = new String[]{" A5", " A3", " Q3", " A4"};
            TOYOTA = new String[]{" Corolla", " Avensis", " Auris", " Verso"};
            FORD = new String[]{" Mondeo", " ECOSPORT", " FOCUS ECOBLUE", " ECOSPORT II"};
            BMW = new String[]{" X3 F25", " X1 F48", "3"};
        }
    }

    private String model;
    private int power;
    private int year;

    public GetMidCar() {
        this.model = DataGeneration.randomModel(new MidCarData());
        this.year = DataGeneration.randomYear();
        this.power = DataGeneration.randomPower(MIN_POWER, MAX_POWER);
    }

    @Override
    public Car randomCar() {
        return new Car.Builder().setModel(model).setPower(power).setYear(year).build();
    }
}
