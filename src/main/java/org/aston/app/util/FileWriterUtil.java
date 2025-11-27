package org.aston.app.util;

import org.aston.app.model.Car;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * Утилитарный класс для записи отсортированных данных об автомобилях в файл.
 * Дополнительное задание 2: запись в файл в режиме добавления
 * <p>
 * Предоставляет метод для добавления информации о результатах сортировки
 * в указанный файл в поддиректории ресурсов проекта.
 */
public class FileWriterUtil {

    /**
     * Форматтер для преобразования текущего времени в строку при записи в файл.
     * Используется формат: "ГГГГ-ММ-ДД ЧЧ:ММ:СС".
     */
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Записывает массив автомобилей в файл в режиме добавления.
     * <p>
     * Добавляет заголовок с названием стратегии сортировки, временную метку,
     * строковое представление каждого автомобиля и разделители.
     * <p>
     * Файл сохраняется в директории src/main/resources.
     *
     * @param fileName         имя файла, в который будет производиться запись
     * @param cars             массив объектов Car, содержащий отсортированные данные
     * @param nameStrategySort название стратегии сортировки для логирования
     * @throws IOException если возникает ошибка ввода-вывода при записи в файл
     */
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
