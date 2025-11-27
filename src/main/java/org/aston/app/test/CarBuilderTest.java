package org.aston.app.test;

import org.aston.app.model.Car;

import static org.aston.app.test.TestUtils.assertTrue;

/**
 * Тестовый класс для проверки функциональности паттерна Builder в классе Car.
 * <p>
 * Содержит методы для тестирования корректного создания объектов Car
 * с использованием билдера, а также проверки значений полей.
 * Для проверок используется утилита TestUtils.
 */
public class CarBuilderTest {
    /**
     * Запускает тесты, связанные с построением объекта Car с помощью Builder.
     * <p>
     * Проверяет, что:
     * - объект создаётся без исключений;
     * - все установленные значения (модель, мощность, год) корректно сохраняются.
     * <p>
     * Выводит сообщение об успешном прохождении теста.
     */
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
