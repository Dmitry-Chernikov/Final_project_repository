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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


/**
 * Дополнительное задание 2: запись в файл в режиме добавления
 */
public class FileWriterUtil {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void appendToFile(String fileName, Car[] cars, String nameStrategySort) {
        try {
            List<String> lines = new ArrayList<>();

            lines.add("=== Стратегия сортировки: " + nameStrategySort + " ===");
            lines.add("Время записи: " + LocalDateTime.now().format(FORMATTER));

            for (Car car : cars) {
                lines.add(car.toString());
            }

            lines.add("");
            lines.add("=".repeat(50));
            lines.add("");

            Path filePath = Paths.get("src", "main", "resources").resolve(fileName);

            Files.write(filePath, lines,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);

            System.out.println("✓ Данные записаны в файл: " + fileName);
            System.out.println("✓ Стратегия: " + nameStrategySort);
            System.out.println("✓ Записано автомобилей: " + cars.length);

        } catch (IOException e) {
            System.err.println("✗ Ошибка при записи в файл: " + e.getMessage());
        }
    }
}
