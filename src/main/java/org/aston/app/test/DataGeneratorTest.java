package org.aston.app.test;

import org.aston.app.model.Car;
import org.aston.app.util.DataGenerator;
import org.aston.app.util.InputValidator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.aston.app.test.TestUtils.assertEquals;
import static org.aston.app.test.TestUtils.assertFalse;
import static org.aston.app.test.TestUtils.assertTrue;

/**
 * Тестовый класс для проверки функциональности класса DataGenerator.
 * <p>
 * Содержит методы для тестирования чтения данных из файла и валидации входных значений.
 * Для проверок используется утилита TestUtils.
 */
public class DataGeneratorTest {
    /**
     * Имя временного тестового файла, используемого для проверки чтения данных.
     */
    private static final String TEST_FILE = "test_data.txt";

    /**
     * Запускает все тесты, связанные с классом DataGenerator.
     * <p>
     * Проверяет:
     * - корректность чтения данных из файла;
     * - валидацию входных данных (мощность, год).
     * <p>
     * Перед тестированием создаётся временный файл с тестовыми данными.
     */
    public static void runTests() {
        System.out.println("Запуск тестов DataGenerator...");

        // Подготовка: создание тестового файла
        try {
            String content = "Toyota,150,2010\nHonda,180,2015\nBMW,300,2020";
            Files.write(Paths.get("src", "main", "resources").resolve(TEST_FILE), content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Ошибка создания тестового файла: " + e.getMessage());
            return;
        }

        // Тест 1: Чтение из файла
       try {
            Car[] cars = DataGenerator.fromFiles(TEST_FILE);
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
        System.out.println("DataGeneratorTest пройден успешно.\n");
    }
}
