package org.aston.app.random;

/**
 * Перечисление CarModels содержит список поддерживаемых моделей автомобилей.
 * Используется для генерации случайных данных в тестах и демо-режиме.
 */
public enum CarModels {
    /**
     * Модель автомобиля Audi.
     */
    AUDI,

    /**
     * Модель автомобиля Toyota.
     */
    TOYOTA,

    /**
     * Модель автомобиля Ford.
     */
    FORD,

    /**
     * Модель автомобиля BMW.
     */
    BMW;

    /**
     * Возвращает строковое представление модели автомобиля по её индексу.
     * <p>
     * Индекс используется как позиция элемента в массиве значений перечисления.
     *
     * @param number индекс модели в списке (0 — AUDI, 1 — TOYOTA и т.д.)
     * @return строковое название модели (например, "AUDI")
     * @throws ArrayIndexOutOfBoundsException если индекс выходит за пределы допустимого диапазона
     */
    public static String models(int number) {
        CarModels[] models = CarModels.values();
        return models[number].toString();
    }
}
