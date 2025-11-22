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
    private static final String[] MODELS = {"Toyota", "BMW", "Audi", "Ford", "Tesla", "Honda", "Mercedes"};

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
        return null;
    }
}
