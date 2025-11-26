package org.aston.app.test;

/**
 * Create by dmitry on 17.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 17.11.2025
 * @project : org.aston.final.project
 * Class CarBuilderTest
 */

import org.aston.app.model.Car;
import static org.aston.app.test.TestUtils.assertTrue;

import static org.aston.app.test.TestUtils.assertTrue;

/**
 * Тесты для проверки корректности Builder-а класса Car
 */
public class CarBuilderTest {
    public static void runTests() {
        System.out.println("Запуск тестов CarBuilder...");

        // Тест 1: Создание объекта через Builder
        Car car = new Car.Builder()
                .setModel("BMW")
                .setPower(300)
                .setYear(2020)
                .build();

        assertTrue("Модель должна быть BMW", car.getModel().equals("BMW"));
        assertTrue("Мощность должна быть 300", car.getPower() == 300);
        assertTrue("Год должен быть 2020", car.getYear() == 2020);

        System.out.println("CarBuilderTest пройден успешно.\n");
    }
}
