package org.aston.app.test;

/**
 * Create by dmitry on 17.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 17.11.2025
 * @project : org.aston.final.project
 * Class CustomTestRunner
 */

/**
 * Главный класс для запуска всех тестов
 */
public class CustomTestRunner {
    public static void main(String[] args) {
        System.out.println("=== Запуск ручных тестов ===\n");

        CarBuilderTest.runTests();
        SortStrategyTest.runTests();
        //DataGeneratorTest.runTests();

        System.out.println("=== Все тесты выполнены ===");
    }
}
