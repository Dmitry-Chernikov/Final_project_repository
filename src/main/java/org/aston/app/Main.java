package org.aston.app;

import org.aston.app.service.SortingApplication;

/**
 * Точка входа в приложение "Система сортировки автомобилей".
 * <p>
 * Класс содержит метод main, который запускает основной цикл работы приложения
 * через экземпляр класса SortingApplication.
 */
public class Main {
    /**
     * Основной метод, с которого начинается выполнение программы.
     * Создаёт экземпляр приложения и запускает его.
     *
     * @param args аргументы командной строки (не используются)
     */
    public static void main(String[] args) {
        SortingApplication app = new SortingApplication();
        app.run();
    }
}
