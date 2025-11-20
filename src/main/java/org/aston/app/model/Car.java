package org.aston.app.model;

import org.aston.app.ecxeption.CarValidationException;

import java.util.Objects;

import static java.time.Year.now;

public class Car implements Comparable<Car> {
    private final String model;
    private final int power;
    private final int year;

    public Car(final String model, final int power, final int year) {
        this.model = model;
        this.power = power;
        this.year = year;
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

    public static class Builder {
        private int power;
        private String model;
        private int year;

        public Builder setPower(final int power) {
            this.power = power;
            return this;
        }

        public Builder setModel(final String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(final int year) {
            this.year = year;
            return this;
        }

        public Car build() {
            validate();
            return new Car(model, power, year);
        }

        private void validate() {
            if (power == 0) {
                throw new CarValidationException("Horsepower is required");
            }
            if (model == null || model.trim().isEmpty()) {
                throw new CarValidationException("Model is required");
            }
            if (year == 0) {
                throw new CarValidationException("Year is required");
            }
            if (year < 1886 || year > now().getValue() + 1) {
                throw new CarValidationException("Invalid year: " + year);
            }
            if (power <= 0) {
                throw new CarValidationException("Horsepower must be positive");
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return power == car.power &&
            year == car.year &&
            model.equals(car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(power, model, year);
    }

    @Override
    public String toString() {
        return "Car{" +
            "horse power=" + power +
            ", model name='" + model + '\'' +
            ", production year=" + year +
            '}';
    }

    @Override
    public int compareTo(Car other) {
        var compare = Integer.compare(year, other.year);
        if (compare != 0) {
            return compare;
        }
        return Integer.compare(power, other.power);
    }

}
