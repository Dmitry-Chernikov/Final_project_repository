package org.aston.app.model;

/**
 * Create by dmitry on 16.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 16.11.2025
 * @project : org.aston.final.project
 * Class Car
 */
public class Car {
    private final String model;
    private final int power;
    private final int year;

    public Car(String model, int power, int year) {
        this.model = model;
        this.power = power;
        this.year = year;
    }
    private Car(Builder builder) {
        this.model = builder.model;
        this.power = builder.power;
        this.year = builder.year;
    }

    public static class Builder {
        private String model;
        private int power;
        private int year;

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setPower(int power) {
            this.power = power;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    public String getModel() {
        return model;
    }

    public int getPower() {
        return power;
    }

    public int getYear() {
        return year;
    }
}
