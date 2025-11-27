package org.aston.app.random;

import org.aston.app.model.Car;

/**
 * Класс GetMidCar реализует интерфейс RandomGeneration и предназначен для создания объектов Car
 * со средней мощностью двигателя (в диапазоне от 110 до 150 л.с.).
 * <p>
 * Использует внутренние данные о моделях автомобилей с ограниченным набором модификаций
 * и генерирует случайные значения в заданных пределах.
 */
public class GetMidCar implements RandomGeneration {

    /**
     * Минимальная мощность, присваиваемая генерируемым автомобилям.
     */
    private final int MIN_POWER = 110;

    /**
     * Максимальная мощность, присваиваемая генерируемым автомобилям.
     */
    private final int MAX_POWER = 150;
    /**
     * Поле для хранения сгенерированного названия модели автомобиля.
     */
    private String model;
    /**
     * Поле для хранения сгенерированной мощности двигателя.
     */
    private int power;
    /**
     * Поле для хранения сгенерированного года выпуска.
     */
    private int year;

    /**
     * Конструктор класса GetMidCar.
     * <p>
     * Инициализирует поля объекта:
     * - модель — случайным образом выбирается на основе MidCarData;
     * - год — случайный в диапазоне от 1990 до 2024;
     * - мощность — случайная в диапазоне от MIN_POWER до MAX_POWER.
     */
    public GetMidCar() {
        this.model = DataGeneration.randomModel(new MidCarData());
        this.year = DataGeneration.randomYear();
        this.power = DataGeneration.randomPower(MIN_POWER, MAX_POWER);
    }

    /**
     * Реализация метода интерфейса RandomGeneration.
     * <p>
     * Создаёт и возвращает новый объект Car с использованием ранее сгенерированных
     * значений модели, мощности и года выпуска.
     *
     * @return объект Car, построенный с помощью Builder
     */
    @Override
    public Car randomCar() {
        return new Car.Builder().setModel(model).setPower(power).setYear(year).build();
    }

    /**
     * Внутренний вложенный класс, расширяющий GetDataCar и инициализирующий массивы модификаций
     * для автомобилей со средней мощностью.
     * <p>
     * Содержит специфичные версии моделей Audi, Toyota, Ford и BMW, соответствующие категории средней мощности.
     */
    class MidCarData extends GetDataCar {
        /**
         * Конструктор инициализирует массивы моделей для каждого производителя,
         * включая пробелы в начале — как часть форматирования полного названия.
         */
        MidCarData() {
            AUDI = new String[]{" A5", " A3", " Q3", " A4"};
            TOYOTA = new String[]{" Corolla", " Avensis", " Auris", " Verso"};
            FORD = new String[]{" Mondeo", " ECOSPORT", " FOCUS ECOBLUE", " ECOSPORT II"};
            BMW = new String[]{" X3 F25", " X1 F48", "3"};
        }
    }
}
