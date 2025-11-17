package org.aston.app.test;

/**
 * Create by dmitry on 17.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 17.11.2025
 * @project : org.aston.final.project
 * Class SortStrategyTest
 */

import org.aston.app.model.Car;
import org.aston.app.strategy.SortByPower;
import org.aston.app.strategy.SortEvenPowerNaturalOddKeep;

/**
 * Ручные тесты для стратегий сортировки
 */
public class SortStrategyTest {
    public static void runTests() {
       /* System.out.println("Запуск тестов SortStrategy...");

        // Тест 1: Сортировка по мощности (возрастание)
        Car[] cars = {
                new Car.Builder().setModel("A").setPower(500).setYear(2000).build(),
                new Car.Builder().setModel("B").setPower(300).setYear(2001).build(),
                new Car.Builder().setModel("C").setPower(400).setYear(2002).build()
        };

        new SortByPower().sort(cars);

        assertTrue("Мощность[0] должна быть 300", cars[0].getPower() == 300);
        assertTrue("Мощность[1] должна быть 400", cars[1].getPower() == 400);
        assertTrue("Мощность[2] должна быть 500", cars[2].getPower() == 500);

        System.out.println("SortByPowerTest пройден успешно.");

        // Тест 2: Сортировка только чётных значений power, нечётные — на месте
        Car[] mixedCars = {
                new Car.Builder().setModel("A").setPower(301).setYear(2000).build(), // нечётное — не двигаем
                new Car.Builder().setModel("B").setPower(300).setYear(2001).build(), // чётное — будет отсортировано
                new Car.Builder().setModel("C").setPower(299).setYear(2002).build(), // нечётное
                new Car.Builder().setModel("D").setPower(200).setYear(2003).build()  // чётное — будет перемещено
        };

        new SortEvenPowerNaturalOddKeep().sort(mixedCars);

        // Позиции нечётных должны остаться прежними
        assertTrue("Car A должен остаться первым", mixedCars[0].getPower() == 301);
        assertTrue("Car C должен остаться третьим", mixedCars[2].getPower() == 299);

        // Чётные должны быть отсортированы: 200, 300
        assertTrue("Ожидается 200", mixedCars[1].getPower() == 200);
        assertTrue("Ожидается 300", mixedCars[3].getPower() == 300);

        System.out.println("SortEvenPowerNaturalOddKeepTest пройден успешно.\n");*/
    }
}
