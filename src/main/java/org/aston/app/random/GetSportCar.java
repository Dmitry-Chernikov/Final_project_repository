package org.aston.app.random;

import org.aston.app.model.Car;


public class GetSportCar implements RandomGeneration {
    private final int MIN_POWER = 300;
    private final int MAX_POWER = 600;

    class SportCarData extends GetDataCar {
        SportCarData() {
            AUDI = new String[]{" R8", " RS"};
            TOYOTA = new String[]{" GR Supra A90", " GR Corolla"};
            FORD = new String[]{" Mustang Shelby GT500", " GT", " MUSTANG DARK HORSE"};
            BMW = new String[]{" M2", " M4", " M8", " X2", " X4", " X5", " X6"};
        }
    }

    private String model;
    private int power;
    private int year;

    public GetSportCar() {
        this.model = DataGeneration.randomModel(new SportCarData());
        this.year = DataGeneration.randomYear();
        this.power = DataGeneration.randomPower(MIN_POWER, MAX_POWER);
    }

    @Override
    public Car randomCar() {
        return new Car.Builder().setModel(model).setPower(power).setYear(year).build();
    }
}
