package org.aston.app.service;

import org.aston.app.model.Car;
import org.aston.app.strategy.SortByModel;
import org.aston.app.strategy.SortByPower;
import org.aston.app.strategy.SortByYear;
import org.aston.app.strategy.SortEvenPowerNaturalOddKeep;
import org.aston.app.strategy.SortStrategy;

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
        showMenu();
        String choice = scanner.nextLine().trim(); // Обработка ввода пользователя

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
