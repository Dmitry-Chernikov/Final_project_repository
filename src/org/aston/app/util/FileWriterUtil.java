package org.aston.app.util;

/**
 * Create by dmitry on 16.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 16.11.2025
 * @project : org.aston.final.project
 * Class FileWriterUtil
 */

import org.aston.app.model.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Дополнительное задание 2: запись в файл в режиме добавления
 */
public static void appendToFile(String filename, Car[] cars, String nameStrategySort) {
        try {
            List<String> lines = new ArrayList<>();
            
            lines.add("Стратегия сортировки: " + nameStrategySort + " ===");
            lines.add("Время записи: " + new Date());
            
            for (Car car : cars) {
                lines.add(car.toString());
            }
            
            lines.add("");
            lines.add("=".repeat(50));
            lines.add("");
            
            Path filePath = Paths.get(filename);
            Files.write(filePath, lines, 
                      StandardOpenOption.CREATE, 
                      StandardOpenOption.APPEND);
            
            System.out.println("✓ Данные записаны в файл: " + filename);
            System.out.println("✓ Стратегия: " + nameStrategySort);
            System.out.println("✓ Записано автомобилей: " + cars.length);
            
        } catch (IOException e) {
            System.err.println("✗ Ошибка при записи в файл: " + e.getMessage());
        }
    }
    
    public static <T> void writeSortedCollection(Collection<T> collection, 
                                               String filename, 
                                               Comparator<T> comparator) {
        try {
            List<T> sortedList = new ArrayList<>(collection);
            if (comparator != null) {
                sortedList.sort(comparator);
            }
            
            List<String> lines = sortedList.stream()
                                         .map(Object::toString)
                                         .collect(Collectors.toList());
            
            Path filePath = Paths.get(filename);
            Files.write(filePath, lines, 
                      StandardOpenOption.CREATE, 
                      StandardOpenOption.APPEND);
            
            System.out.println("✓ Отсортированная коллекция записана в: " + filename);
            System.out.println("✓ Записано элементов: " + lines.size());
            
        } catch (IOException e) {
            System.err.println("✗ Ошибка записи: " + e.getMessage());
        }
    }
    
    public static void writeFoundValues(List<?> values, String filename) {
        try {
            List<String> lines = values.stream()
                                     .map(Object::toString)
                                     .collect(Collectors.toList());
            
            Path filePath = Paths.get(filename);
            Files.write(filePath, lines,
                      StandardOpenOption.CREATE,
                      StandardOpenOption.APPEND);
            
            System.out.println("✓ Найденные значения записаны в: " + filename);
            System.out.println("✓ Записано элементов: " + lines.size());
            
        } catch (IOException e) {
            System.err.println("✗ Ошибка записи: " + e.getMessage());
        }
    }
    
    public static void writeCarsToFile(List<Car> cars, String filename, String sortBy) {
        try {
            List<Car> sortedCars = new ArrayList<>(cars);
            
            switch (sortBy.toLowerCase()) {
                case "brand":
                    sortedCars.sort(Comparator.comparing(Car::getBrand));
                    break;
                case "model":
                    sortedCars.sort(Comparator.comparing(Car::getModel));
                    break;
                case "year":
                    sortedCars.sort(Comparator.comparingInt(Car::getYear));
                    break;
                case "power":
                    sortedCars.sort(Comparator.comparingInt(Car::getPower));
                    break;
                default:
            }
            
            List<String> lines = new ArrayList<>();
            lines.add("Автомобили (сортировка: " + sortBy + ") ===");
            lines.add("Количество: " + sortedCars.size());
            lines.add("");
            
            for (Car car : sortedCars) {
                lines.add(car.toString());
            }
            
            lines.add("");
            lines.add("=".repeat(50));
            lines.add("");
            
            Path filePath = Paths.get(filename);
            Files.write(filePath, lines,
                      StandardOpenOption.CREATE,
                      StandardOpenOption.APPEND);
            
            System.out.println("✓ Автомобили записаны в файл: " + filename);
            System.out.println("✓ Сортировка: " + sortBy);
            System.out.println("✓ Записано: " + sortedCars.size() + " автомобилей");
            
        } catch (IOException e) {
            System.err.println("✗ Ошибка записи: " + e.getMessage());
        }
    }
