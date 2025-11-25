package org.aston.app.util;

import org.aston.app.model.Car;
import org.aston.app.random.*;

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
        Car[] randomCar = new Car[count];  // создание массива машин
        GenerationCar generationCar = null; // переменная для генерации случайного типа манины
        Random random = new Random();
        int min = 0;// min и max для выбора типа машины
        int max = 4;

        for (int i = 0; i < count; i++) { // заполнение массива
            int randCount = random.nextInt(max - min) + min;
            switch (randCount) {
                case 0:
                    generationCar = new GenerationCar(new GetLowpowerCar());
                    break;
                case 1:
                    generationCar = new GenerationCar(new GetMidCar());
                    break;
                case 2:
                    generationCar = new GenerationCar(new GetPowerfulCar());
                    break;
                case 3:
                    generationCar = new GenerationCar(new GetSportCar());
                    break;
            }

            randomCar[i] = generationCar.generationRandomCar();
        }
        return randomCar;
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
