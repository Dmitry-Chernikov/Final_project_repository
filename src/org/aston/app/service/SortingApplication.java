package org.aston.app.service;

import org.aston.app.model.Car;
import org.aston.app.strategy.SortByModel;
import org.aston.app.strategy.SortByPower;
import org.aston.app.strategy.SortByYear;
import org.aston.app.strategy.SortEvenPowerNaturalOddKeep;
import org.aston.app.strategy.SortStrategy;
import org.aston.app.util.*;

import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Create by dmitry on 16.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 16.11.2025
 * @project : org.aston.final.project
 * Class SortingApplication
 */
public class SortingApplication {
    private final Scanner scanner;

    public SortingApplication() {
        this.scanner = new Scanner(System.in);
    }
    public void run() {
        while (true) { // Бесконечный цикл для меню
            showMenu();
            String choice = scanner.nextLine().trim(); // Обработка ввода пользователя
            if ("0".equals(choice)){
                System.out.println("Выход из программы");
                break;
            }
            if (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice)) {
                System.out.println("Неверный выбор. Попробуйте ещё раз.");
                continue;
            }

            System.out.println("Введите количество автомобилей: ");
            int count;
            try{
                count = Integer.parseInt(scanner.nextLine().trim());
                if (count <= 0) {
                    System.out.println("Количество должно быть больше 0.");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное число.");
                continue;
            }

            Car[] cars = null;

            switch (choice) {
                case "1":
                    cars = DataGenerator.generateRandom(count);
                    break;
                case "2":
                    try {
                        System.out.println("Введите путь к файлу: ");
                        String path = scanner.nextLine().trim();
                        cars = DataGenerator.fromFiles(path);
                    } catch (Exception | NumberFormatException | IllegalFormatException e) {
                        System.out.println("Ошибка при чтении файла: " + e.getMessage());
                        continue;
                    }
                    break;
                case "3":
                    cars = DataGenerator.inputManually(scanner, count);
                    break;
            }
            System.out.println("Исходный данные: ");
            printArray(cars);

            SortStrategy sortStrategy = getSortStrategy();
            if (sortStrategy != null) continue;

            sortStrategy.sort(cars);

            System.out.println("Отсортированные данные: ");
            printArray(cars);

            // Доп. задание 2: запись в файл
            System.out.println("Сохранить результат в файл? (y/n): ");
            if ("y".equalsIgnoreCase(scanner.nextLine().trim())){
                System.out.println("Введите путь к файлу: ");
                String filename = scanner.nextLine().trim();
                FileWriterUtil.appendToFile(filename, cars, sortStrategy.getClass().getSimpleName());
                System.out.println("Данные сохранены в файл: " + filename);
            }

            // Доп. задание 4: подсчёт вхождений заданного power
            System.out.println("Запустить подсчёт вхождений заданного power? (y/n): ");
            if ("y".equalsIgnoreCase(scanner.nextLine().trim())) {
                System.out.println("Введите значение power для поиска: ");
                try{
                    int targetPower = Integer.parseInt(scanner.nextLine().trim());
                    countOccurrencesParallel(cars, targetPower);
                }catch (NumberFormatException e) {
                    System.out.println("Введите корректное значение power.");
                }
            }
        }
        scanner.close();
    }
    private void showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("\n === Меню === \n")
                    .append("1. Заполнить случайно \n")
                    .append("2. Заполнить из файла \n")
                    .append("3. Вывести вручную \n")
                    .append("0. Выход \n")
                    .append("Выберете способ заполнения: ");
        System.out.println(menu);
    }
    private SortStrategy getSortStrategy() {
        StringBuilder menu = new StringBuilder();
        menu.append("\n Выберите поле по которому будет сортировка: \n")
                    .append("1. По модели \n")
                    .append("2. По мощности \n")
                    .append("3. По году \n")
                    .append("4. По чётным/нечётным (по полю мощность, чётные - сортировать) \n")
                    .append("Ваш выбор: ");
        System.out.println(menu);

        String choice = scanner.nextLine().trim();

        return switch (choice) {
            case "1" -> new SortByModel();
            case "2" -> new SortByPower();
            case "3" -> new SortByYear();
            case "4" -> new SortEvenPowerNaturalOddKeep();
            default -> {
                System.out.println("Неверный выбор");
                yield null; // Возвращаем null, если выбор неверный yield это ключевое слово для возврата значения из switch Java 14
            }
        };
    }
    private void printArray(Car[] cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    /**
     * Дополнительное задание 4: многопоточный подсчет вхождений
     */
    private void countOccurrencesParallel(Car[] cars, int targetPower) {
        int processors = Runtime.getRuntime().availableProcessors(); // Получаем количество процессоров
        ExecutorService executorService = Executors.newFixedThreadPool(processors); // Создаем пул потоков

        int chunkSize = Math.max(1, cars.length / processors); // Количество элементов в каждом потоке
        Counter counter = new Counter(); // Счетчик для подсчета количества элементов с заданной мощностью

        for (int i = 0; i < cars.length; i += chunkSize) { // Разбиваем массив на части
            int start = i;
            int end = Math.min(i + chunkSize, cars.length);

            executorService.submit(() -> { // Запускаем поток
               int localCount = 0;
               for (int j = start; j < end; j++) { // Проходим по части массива и считаем количество элементов с заданной мощностью
                   if (cars[j].getPower() == targetPower) { // Проверяем мощность элемента
                       localCount++;
                   }
               }
               synchronized (counter) { // Синхронизируем доступ к общему ресурсу
                   counter.value += localCount;
               }
            });
        }
        executorService.shutdown(); // Останавливаем потоки
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS); // Ожидаем завершения всех потоков
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Восстанавливаем прерванность потока
        }
        System.out.println("Количество элементов с мощностью " + targetPower + ": " + counter.value);
    }
    private static class Counter {
        int value = 0; // Счетчик
    }
}
