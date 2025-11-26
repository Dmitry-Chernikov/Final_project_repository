package org.aston.app.random;

import org.aston.app.model.Car;

public class GetLowpowerCar implements RandomGeneration {

    private final int MIN_POWER = 87;
    private final int MAX_POWER = 110;

    class LowPowerCarData extends GetDataCar {
        LowPowerCarData() {
            AUDI = new String[]{" A3", " 100", " A4"};
            TOYOTA = new String[]{" Corolla", " Probox"};
            FORD = new String[]{" FUSION", " FIESTA", " FOCUS POWERSHIFT", " C-MAX 1.6 TDCI"};
            BMW = new String[]{" 1"};
        }
    }

    private String model;
    private int power;
    private int year;

    public GetLowpowerCar() {
        this.model = DataGeneration.randomModel(new LowPowerCarData());
        this.year = DataGeneration.randomYear();
        this.power = DataGeneration.randomPower(MIN_POWER, MAX_POWER);
    }

    @Override
    public Car randomCar() {
        return new Car.Builder().setModel(model).setPower(power).setYear(year).build();
    }
}
