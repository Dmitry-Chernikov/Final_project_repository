package org.aston.app.strategy;

/**
 * Create by dmitry on 16.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 16.11.2025
 * @project : org.aston.final.project
 * Class SortEvenPowerNaturalOddKeep
 */

import org.aston.app.model.Car;

/**
 * Дополнительное задание 1: сортировка по полю power.
 * Четные значения сортируются по возрастанию, нечетные остаются на своих местах.
 */
public class SortEvenPowerNaturalOddKeep implements SortStrategy<Car> {
    @Override
    public void sort(Car[] cars) {
        // Извлекаем четные значения мощности
        Car[] carEvenPowers = new Car[cars.length];
        int evenCount = 0;

        for (Car car : cars) {
            if (car.getPower() % 2 == 0) {
                carEvenPowers[evenCount++] = car;
            }
        }

        // Сортируем четные значения
        quickSort(carEvenPowers, 0, evenCount - 1);

        // Возвращаем отсортированные четные значения обратно в массив
        int evenIndex = 0;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].getPower() % 2 == 0) {
                int originalPower = cars[i].getPower();
                // Находим отсортированное значение
                Car carNewPower = carEvenPowers[evenIndex++];
                if (carNewPower.getPower() != originalPower) {
                    // Пересоздаем объект
                    cars[i] = carNewPower;
                }
            }
        }
    }

    @Override
    public int partition(Car[] cars, int low, int high) {
        Integer pivot = cars[high].getPower(); // Опорный элемент — последний
        int i = low - 1; // Индекс меньшего элемента

        for (int j = low; j < high; j++) {
            if ( ((Integer)cars[j].getPower()).compareTo(pivot) <= 0) { // Если текущий элемент меньше или равен опорному
                i++;
                swap(cars, i, j); // Меняем местами элементы
            }
        }
        swap(cars, i + 1, high); // Меняем местами опорный элемент с элементом на позиции i+1
        return i + 1;
    }
}
