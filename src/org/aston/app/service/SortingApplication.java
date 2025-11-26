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
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

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
    private Car[] lastSortedCars; // Сохраняем последний отсортированный массив

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
            
            // Обновляем проверку ввода для новых пунктов меню
            if (!"1".equals(choice) && !"2".equals(choice) && !"3".equals(choice) && 
                !"4".equals(choice) && !"5".equals(choice) && !"6".equals(choice)) {
                System.out.println("Неверный выбор. Попробуйте ещё раз.");
                continue;
            }

            // Для пунктов 1-3 обрабатываем как раньше
            if ("1".equals(choice) || "2".equals(choice) || "3".equals(choice)) {
                processCarGeneration(choice);
            } 
            // Новые пункты меню для записи в файл
            else if ("4".equals(choice)) {
                writeSortedCollectionToFile();
            } else if ("5".equals(choice)) {
                writeFoundCarsToFile();
            } else if ("6".equals(choice)) {
                writeCustomSortedCarsToFile();
            }
        }
        scanner.close();
    }
    
    private void processCarGeneration(String choice) {
        System.out.println("Введите количество автомобилей: ");
        int count;
        try{
            count = Integer.parseInt(scanner.nextLine().trim());
            if (count <= 0) {
                System.out.println("Количество должно быть больше 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Введите корректное число.");
            return;
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
                    return;
                }
                break;
            case "3":
                cars = DataGenerator.inputManually(scanner, count);
                break;
        }
        
        System.out.println("Исходные данные: ");
        printArray(cars);

        SortStrategy sortStrategy = getSortStrategy();
        if (sortStrategy == null) return;

        sortStrategy.sort(cars);
        this.lastSortedCars = cars; // Сохраняем отсортированный массив

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
    
    private void showMenu() {
        StringBuilder menu = new StringBuilder();
        menu.append("\n === Меню === \n")
            .append("1. Заполнить случайно \n")
            .append("2. Заполнить из файла \n")
            .append("3. Вывести вручную \n")
            .append("4. Записать отсортированную коллекцию в файл \n")
            .append("5. Записать найденные автомобили в файл \n")
            .append("6. Записать автомобили с сортировкой по полю \n")
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
                yield null;
            }
        };
    }
    
    private void printArray(Car[] cars) {
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private void writeSortedCollectionToFile() {
        if (lastSortedCars == null || lastSortedCars.length == 0) {
            System.out.println("Нет данных для записи. Сначала сгенерируйте и отсортируйте автомобили.");
            return;
        }
        
        System.out.println("Введите имя файла для сохранения: ");
        String filename = scanner.nextLine().trim();
        
        // Преобразуем массив в список для использования в FileWriterUtil
        List<Car> carList = List.of(lastSortedCars);
        FileWriterUtil.writeSortedCollection(carList, filename, null);
    }
    
    private void writeFoundCarsToFile() {
        if (lastSortedCars == null || lastSortedCars.length == 0) {
            System.out.println("Нет данных для поиска. Сначала сгенерируйте и отсортируйте автомобили.");
            return;
        }
        
        System.out.print("Введите марку для поиска: ");
        String searchBrand = scanner.nextLine().trim();
        
        List<Car> foundCars = new ArrayList<>();
        for (Car car : lastSortedCars) {
            if (car.getBrand().toLowerCase().contains(searchBrand.toLowerCase())) {
                foundCars.add(car);
            }
        }
        
        if (foundCars.isEmpty()) {
            System.out.println("Автомобили с маркой '" + searchBrand + "' не найдены.");
        } else {
            System.out.println("Введите имя файла для сохранения: ");
            String filename = scanner.nextLine().trim();
            FileWriterUtil.writeFoundValues(foundCars, filename);
            System.out.println("Найдено автомобилей: " + foundCars.size());
        }
    }
    
    private void writeCustomSortedCarsToFile() {
        if (lastSortedCars == null || lastSortedCars.length == 0) {
            System.out.println("Нет данных для записи. Сначала сгенерируйте автомобили.");
            return;
        }
        
        System.out.println("Выберите поле для сортировки:");
        System.out.println("1 - Марка, 2 - Модель, 3 - Год, 4 - Мощность");
        String sortChoice = scanner.nextLine().trim();
        
        System.out.println("Введите имя файла для сохранения: ");
        String filename = scanner.nextLine().trim();
        
        String sortBy;
        switch (sortChoice) {
            case "1": sortBy = "brand"; break;
            case "2": sortBy = "model"; break;
            case "3": sortBy = "year"; break;
            case "4": sortBy = "power"; break;
            default: 
                System.out.println("Неверный выбор, используется сортировка по марке");
                sortBy = "brand";
        }
        
        // Преобразуем массив в список
        List<Car> carList = new ArrayList<>(List.of(lastSortedCars));
        FileWriterUtil.writeCarsToFile(carList, filename, sortBy);
    }

    /**
     * Дополнительное задание 4: многопоточный подсчет вхождений
     */
    private void countOccurrencesParallel(Car[] cars, int targetPower) {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(processors);

        int chunkSize = Math.max(1, cars.length / processors);
        Counter counter = new Counter();

        for (int i = 0; i < cars.length; i += chunkSize) {
            int start = i;
            int end = Math.min(i + chunkSize, cars.length);

            executorService.submit(() -> {
               int localCount = 0;
               for (int j = start; j < end; j++) {
                   if (cars[j].getPower() == targetPower) {
                       localCount++;
                   }
               }
               synchronized (counter) {
                   counter.value += localCount;
               }
            });
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Количество элементов с мощностью " + targetPower + ": " + counter.value);
    }
    
    private static class Counter {
        int value = 0;
    }
}
