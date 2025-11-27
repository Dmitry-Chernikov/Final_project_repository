package org.aston.app.random;

import java.util.Random;

/**
 * Утилитарный класс для генерации случайных данных об автомобилях.
 * <p>
 * Содержит статические методы для генерации случайной модели, мощности и года выпуска.
 * Используется для заполнения коллекций тестовыми данными в режиме генерации.
 */
public class DataGeneration {

    /**
     * Генератор случайных чисел, используемый для всех операций случайного выбора.
     */
    static Random meaning = new Random();

    /**
     * Генерирует случайное полное название модели автомобиля.
     * <p>
     * Сначала выбирается производитель из перечисления {@link CarModels},
     * затем к нему добавляется случайная модификация из переданного объекта {@link GetDataCar}.
     *
     * @param getDataCar объект, содержащий массивы модификаций для каждого производителя
     * @return строка с полным названием модели (например, "AUDIA6")
     */
    static String randomModel(GetDataCar getDataCar) {

        int countElements = CarModels.values().length;
        int numder = meaning.nextInt(countElements);
        String modelName = CarModels.models(numder);

        if (numder == 0) {
            int random = meaning.nextInt(getDataCar.AUDI.length);
            modelName = modelName + getDataCar.AUDI[random];
        }

        if (numder == 1) {
            int random = meaning.nextInt(getDataCar.TOYOTA.length);
            modelName = modelName + getDataCar.TOYOTA[random];
        }

        if (numder == 2) {
            int random = meaning.nextInt(getDataCar.FORD.length);
            modelName = modelName + getDataCar.FORD[random];
        }

        if (numder == 3) {
            int random = meaning.nextInt(getDataCar.BMW.length);
            modelName = modelName + getDataCar.BMW[random];
        }

        return modelName;
    }

    /**
     * Генерирует случайное значение мощности в заданном диапазоне.
     * <p>
     * Возвращает значение от minPower до maxPower (исключая maxPower).
     * <p>
     * <b>Внимание:</b> в текущей реализации используется ошибка:
     * возвращаемое значение вычисляется как {@code meaning.nextInt(maxPower - minPower) + maxPower},
     * что приводит к значениям выше maxPower. Корректная реализация — прибавление к minPower.
     *
     * @param minPower минимальное значение мощности (включительно)
     * @param maxPower максимальное значение мощности (исключительно)
     * @return случайная мощность в указанном диапазоне
     */
    static int randomPower(int minPower, int maxPower) {
        return meaning.nextInt(maxPower - minPower) + maxPower;
    }

    /**
     * Генерирует случайный год выпуска автомобиля.
     * <p>
     * Возвращает значение в диапазоне от 1990 до 2024 года включительно.
     * Диапазон задан жёстко: 35 лет от начальной точки 1990.
     *
     * @return случайный год выпуска от 1990 до 2024
     */
    static int randomYear() {
        return 1990 + meaning.nextInt(35);
    }
}
