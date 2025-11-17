package org.aston.app.test;

/**
 * Create by dmitry on 17.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 17.11.2025
 * @project : org.aston.final.project
 * Class DtataGeneratorTest
 */

import org.aston.app.model.Car;
import org.aston.app.util.DataGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Тесты для DataGenerator
 */
public class DtataGeneratorTest {
    private static final String TEST_FILE = "test_data.txt";

    public static void runTests() {
        System.out.println("Запуск тестов DataGenerator...");

        // Подготовка: создание тестового файла
        try {
            String content = "Toyota,150,2010\nHonda,180,2015\nBMW,300,2020";
            Files.write(Paths.get(TEST_FILE), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Ошибка создания тестового файла: " + e.getMessage());
            return;
        }

        // Тест 1: Чтение из файла
      /*  try {
            Car[] cars = DataGenerator.fromFile(TEST_FILE);
            assertEquals("Должно быть 3 автомобиля", 3, cars.length);
            assertTrue("Первый — Toyota", cars[0].getModel().equals("Toyota"));
            assertTrue("Последний — BMW", cars[2].getModel().equals("BMW"));
            System.out.println("DataGenerator.fromFileTest пройден успешно.");
        } catch (Exception e) {
            System.out.println("Тест fromFile провален: " + e.getMessage());
        }

        // Тест 2: Валидация данных
        assertTrue("Мощность 150 — валидна", InputValidator.isValidPower(150));
        assertFalse("Мощность -10 — невалидна", InputValidator.isValidPower(-10));
        assertTrue("Год 2020 — валиден", InputValidator.isValidYear(2020));
        System.out.println("DataGeneratorTest пройден успешно.\n");*/
    }
}
