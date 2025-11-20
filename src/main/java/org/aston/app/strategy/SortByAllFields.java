package org.aston.app.strategy;

import org.aston.app.model.Car;
import org.aston.app.model.CarModelComparator;
import org.aston.app.model.CarPowerComparator;
import org.aston.app.model.CarYearComparator;

import java.util.Comparator;

/**
 * Create by dmitry on 20.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 20.11.2025
 * @project : org.aston.final.project
 * Class SortByAllFields
 */
public class SortByAllFields implements SortStrategy {
    @Override
    public void sort(Car[] cars) {
        Comparator<Car> totalComparator = new CarModelComparator().thenComparing(new CarPowerComparator()).thenComparing(new CarYearComparator());
        Car[] sortedCars = new Car[cars.length];
        for (int i = 0; i < cars.length; i++) {
            for (int j = 0; j < cars.length; j++) {
                if (totalComparator.compare(cars[i], cars[j]) < 0) {
                    sortedCars[i] = cars[j];
                }
            }
        }
    }

    public void sortComparable(Car[] cars) {
        for (int i = 0; i < cars.length; i++) {
            for (int j = 0; j < cars.length; j++) {
                if (cars[i].compareTo(cars[j]) < 0) {
                    Car temp = cars[i];
                    cars[i] = cars[j];
                    cars[j] = temp;
                }
            }
        }
    }
}
