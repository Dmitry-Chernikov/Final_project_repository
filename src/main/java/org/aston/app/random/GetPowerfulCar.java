package org.aston.app.random;

import org.aston.app.model.Car;


public class GetPowerfulCar implements RandomGeneration {

    private final int MIN_POWER = 150;
    private final int MAX_POWER = 300;

    class PowerfulCarData extends GetDataCar {
        PowerfulCarData() {
            AUDI = new String[]{" A5", " Q7", " Q5", " A8", " A7"};
            TOYOTA = new String[]{" Camry", " Highlander", " RAV4", " Land Cruiser"};
            FORD = new String[]{" KUGA", " ECOBOOST", " FORD EXPLORER VI"};
            BMW = new String[]{"X3 G01", " 4", " X5 G05", " 7"};
        }
    }

    private String model;
    private int power;
    private int year;

    public GetPowerfulCar() {
        this.model = DataGeneration.randomModel(new PowerfulCarData());
        this.year = DataGeneration.randomYear();
        this.power = DataGeneration.randomPower(MIN_POWER, MAX_POWER);
    }

    @Override
    public Car randomCar() {
        return new Car.Builder().setModel(model).setPower(power).setYear(year).build();
    }
}

