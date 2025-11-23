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
 * Class SortByAllFieldsComparator
 */
public class SortByAllFieldsComparator implements SortStrategy<Car> {
    private Comparator<Car> totalComparator = new CarModelComparator().thenComparing(new CarPowerComparator()).thenComparing(new CarYearComparator());

    @Override
    public void sort(Car[] cars) {
        quickSort(cars, 0, cars.length - 1);
    }

    @Override
    public int partition(Car[] cars, int low, int high) {
        Car pivot = cars[high]; // Опорный элемент — последний
        int i = low - 1; // Индекс меньшего элемента

        for (int j = low; j < high; j++) {
            if (totalComparator.compare(cars[j], pivot)  <= 0) { // Если текущий элемент меньше или равен опорному
                i++;
                swap(cars, i, j); // Меняем местами элементы
            }
        }
        swap(cars, i + 1, high); // Меняем местами опорный элемент с элементом на позиции i+1
        return i + 1;
    }
}
