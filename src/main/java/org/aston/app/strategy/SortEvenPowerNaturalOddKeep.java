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
public class SortEvenPowerNaturalOddKeep implements SortStrategy {
    @Override
    public void sort(Car[] cars) {
        // Извлекаем четные значения мощности
        int[] evenPowers = new int[cars.length];
        int evenCount = 0;

        for (Car car : cars) {
            if (car.getPower() % 2 == 0) {
                evenPowers[evenCount++] = car.getPower();
            }
        }

        // Сортируем четные значения
        for (int i = 0; i < evenCount - 1; i++) {
            for (int j = 0; j < evenCount - 1 - i; j++) {
                if (evenPowers[j] > evenPowers[j + 1]) {
                    int temp = evenPowers[j];
                    evenPowers[j] = evenPowers[j + 1];
                    evenPowers[j + 1] = temp;
                }
            }
        }

        // Возвращаем отсортированные четные значения обратно в массив
        int evenIndex = 0;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].getPower() % 2 == 0) {
                int originalPower = cars[i].getPower();
                // Находим отсортированное значение
                int newPower = evenPowers[evenIndex++];
                if (newPower != originalPower) {
                    // Пересоздаем объект через Builder (неизменяемость)
                    Car newCar = new Car.Builder()
                            .setModel(cars[i].getModel())
                            .setPower(newPower)
                            .setYear(cars[i].getYear())
                            .build();
                    cars[i] = newCar;
                }
            }
        }
    }
}
