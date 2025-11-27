package org.aston.app.service;

import org.aston.app.model.Car;
import org.aston.app.strategy.SortByAllFieldsComparator;
import org.aston.app.strategy.SortByAllFilesComparable;
import org.aston.app.strategy.SortByModel;
import org.aston.app.strategy.SortByPower;
import org.aston.app.strategy.SortByYear;
import org.aston.app.strategy.SortEvenPowerNaturalOddKeep;
import org.aston.app.strategy.SortStrategy;
import org.aston.app.test.CustomTestRunner;
import org.aston.app.util.DataGenerator;
import org.aston.app.util.FileWriterUtil;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Основной класс приложения, реализующий цикл взаимодействия с пользователем.
 * <p>
 * Предоставляет меню для выбора способа заполнения данных, метода сортировки и
 * дополнительных действий (запись в файл, подсчёт вхождений). Использует паттерн "Стратегия"
 * для реализации различных алгоритмов сортировки.
 */
public class SortingApplication {
    /**
     * Сканер для чтения пользовательского ввода из консоли.
     */
    private final Scanner scanner;

    /**
     * Конструктор класса.
     * Инициализирует сканер для чтения ввода.
     */
    public SortingApplication() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Открывает HTML-файл в браузере по умолчанию.
     *
     * @param path путь к HTML-файлу
     */
    public static void openHtmlFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(file.toURI());
                    System.out.println("✅ Открыт index.html в браузере: " + file.getAbsolutePath());
                } else {
                    System.err.println("❌ Desktop не поддерживается на этой системе.");
                }
            } else {
                System.err.println("❌ Файл не найден: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.err.println("❌ Ошибка открытия файла: " + e.getMessage());
        }
    }

    /**
     * Основной метод, запускающий цикл приложения.
     * <p>
     * Отображает меню, обрабатывает выбор пользователя, запускает тесты,
     * генерирует данные, выполняет сортировку, записывает результаты в файл
     * и подсчитывает вхождения заданного значения мощности.
     */
    public void run() {
        while (true) { // Бесконечный цикл для меню
            showMenu();
            String choice = scanner.nextLine().trim(); // Обработка ввода пользователя
            if ("0".equals(choice)){
                System.out.println("Выход из программы");
                break;
            }
            if ("4".equals(choice)) {
                CustomTestRunner.run();
                continue;
            }
            if ("5".equals(choice)) {
                StringBuilder authors = new StringBuilder();
                authors.append("\nАвторы:\n")
                        .append("Дмитрий Черников \n")
                        .append("Лучкин Дмитрий \n")
                        .append("Иван Феофанов \n")
                        .append("Наталия Абызова \n")
                        .append("Максим Кустков \n");

                System.out.print(authors);
                continue;
            }
            if ("6".equals(choice)) {
                openHtmlFile(Paths.get("doc", "html").resolve("index.html").toString());
                continue;
            }

            if (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice)) {
                System.out.println("Неверный выбор. Попробуйте ещё раз.");
                continue;
            }

            System.out.print("Введите количество автомобилей: ");
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
                        System.out.print("Введите путь к файлу, например - cars_input_list.txt : ");
                        cars = DataGenerator.fromFiles(scanner.nextLine().trim());
                    } catch (Exception  e) {
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

            if (sortStrategy == null) {
                continue;
            }

            sortStrategy.sort(cars);

            System.out.println("Отсортированные данные: ");
            printArray(cars);

            // Доп. задание 2: запись в файл
            System.out.print("Сохранить результат в файл? (y/n): ");
            if ("y".equalsIgnoreCase(scanner.nextLine().trim())){
                System.out.print("Введите имя файла, например - output.txt : ");
                String filename = scanner.nextLine().trim();
                FileWriterUtil.appendToFile(filename, cars, sortStrategy.getClass().getSimpleName());
                System.out.println("Данные сохранены в файл: " + filename);
                System.out.println();
            }

            // Доп. задание 4: подсчёт вхождений заданного power
            System.out.print("Запустить подсчёт вхождений заданного power? (y/n): ");
            if ("y".equalsIgnoreCase(scanner.nextLine().trim())) {
                System.out.print("Введите значение power для поиска: ");
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

    /**
     * Отображает главное меню приложения с возможными действиями.
     */
    private void showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("\n === Меню === \n")
                    .append("1. Заполнить случайно \n")
                    .append("2. Заполнить из файла \n")
                    .append("3. Вывести вручную \n")
                .append("4. Запустить тесты \n")
                .append("5. Об авторах \n")
                .append("6. Документация \n")
                    .append("0. Выход \n")
                    .append("Выберете способ заполнения: ");
        System.out.print(menu);
    }

    /**
     * Запрашивает у пользователя выбор стратегии сортировки.
     * <p>
     * Возвращает соответствующую реализацию SortStrategy или null при неверном выборе.
     *
     * @return выбранная стратегия сортировки или null
     */
    private SortStrategy getSortStrategy() {
        StringBuilder menu = new StringBuilder();
        menu.append("\n Выберите поле по которому будет сортировка: \n")
                    .append("1. По всем полям Comparator \n")
                    .append("2. По всем полям Comparable \n")
                    .append("3. По модели \n")
                    .append("4. По мощности \n")
                    .append("5. По году \n")
                    .append("6. По чётным/нечётным (по полю мощность, чётные - сортировать) \n")
                    .append("Ваш выбор: ");
        System.out.print(menu);

        String choice = scanner.nextLine().trim();

        return switch (choice) {
            case "1" -> new SortByAllFieldsComparator();
            case "2" -> new SortByAllFilesComparable();
            case "3" -> new SortByModel();
            case "4" -> new SortByPower();
            case "5" -> new SortByYear();
            case "6" -> new SortEvenPowerNaturalOddKeep();
            default -> {
                System.out.println("Неверный выбор");
                yield null; // Возвращаем null, если выбор неверный yield это ключевое слово для возврата значения из switch Java 14
            }
        };
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

    /**
     * Выводит все элементы массива автомобилей в консоль.
     *
     * @param cars массив объектов Car для вывода
     */
    private void printArray(Car[] cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    /**
     * Внутренний класс для хранения счётчика вхождений.
     * Используется для атомарного обновления значения из нескольких потоков.
     */
    private static class Counter {
        int value = 0; // Счетчик
    }
}
