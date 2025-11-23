package org.aston.app.util;

import org.aston.app.model.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * Create by dmitry on 16.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 16.11.2025
 * @project : org.aston.final.project
 * Class DataGenerator
 */
public class DataGenerator {

    private static final Random random = new Random();
    public static final String[] MODELS = {"Toyota", "BMW", "Audi", "Ford", "Tesla", "Honda", "Mercedes"};

    public static Car[] generateRandom(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> {
                    String model = MODELS[random.nextInt(MODELS.length)];
                    int power = 80 + random.nextInt(500);
                    int year = 1990 + random.nextInt(35);

                    return new Car.Builder()
                            .setModel(model)
                            .setPower(power)
                            .setYear(year)
                            .build();
                }).toArray(Car[]::new);
    }
    public static Car[] fromFiles(String path) throws IOException {
        return Files.lines(Paths.get(path))
                .map(line -> {
                    String[] parts = line.split(",");
                    if (parts.length != 3) {
                        throw new IllegalArgumentException("Некорректный формат: " + line);
                    }
                    String model = parts[0].trim();
                    int power = Integer.parseInt(parts[1].trim());
                    int year = Integer.parseInt(parts[2].trim());

                    return new Car.Builder()
                            .setModel(model)
                            .setPower(power)
                            .setYear(year)
                            .build();
                })
                .toArray(Car[]::new);
    }
    public static Car[] inputManually(Scanner scanner , int count) {
        CustomCarCollection collection = new CustomCarCollection(count);
        for (int i = 0; i < count; i++) {
            System.out.println("Введите данные для автомобиля " + (i + 1) + ":");

            String model;
            do {
                System.out.print("Модель из списка (\"Toyota\", \"BMW\", \"Audi\", \"Ford\", \"Tesla\", \"Honda\", \"Mercedes\"): ");
                model = scanner.nextLine().trim();
                if (!InputValidator.isValidModel(model)) {
                    System.out.println("Некорректная модель. Повторите ввод.");
                }
            } while (!InputValidator.isValidModel(model));

            int power = 0;
            do {
                System.out.print("Мощность (л.с.): ");
                try {
                    power = Integer.parseInt(scanner.nextLine().trim());
                    if (!InputValidator.isValidPower(power)) {
                        System.out.println("Мощность должна быть от 1 до 2000.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Введите корректное число.");
                    power = 0;
                }
            } while (!InputValidator.isValidPower(power));

            int year = 0;
            do {
                System.out.print("Год производства: ");
                try {
                    year = Integer.parseInt(scanner.nextLine().trim());
                    if (!InputValidator.isValidYear(year)) {
                        System.out.println("Год должен быть от 1885 до 2025.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Введите корректное число.");
                    year = 0;
                }
            } while (!InputValidator.isValidYear(year));

            Car car = new Car.Builder()
                    .setModel(model)
                    .setPower(power)
                    .setYear(year)
                    .build();
            collection.add(car);
        }
        return collection.toArray();
    }
}
