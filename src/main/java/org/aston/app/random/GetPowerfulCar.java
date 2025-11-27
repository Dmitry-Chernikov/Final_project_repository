package org.aston.app.random;

import org.aston.app.model.Car;

/**
 * Класс GetPowerfulCar реализует интерфейс RandomGeneration и предназначен для создания объектов Car
 * с высокой мощностью двигателя (в диапазоне от 150 до 300 л.с.).
 * <p>
 * Использует внутренние данные о моделях автомобилей с ограниченным набором модификаций
 * и генерирует случайные значения в заданных пределах.
 */
public class GetPowerfulCar implements RandomGeneration {

    /**
     * Минимальная мощность, присваиваемая генерируемым автомобилям.
     */
    private final int MIN_POWER = 150;

    /**
     * Максимальная мощность, присваиваемая генерируемым автомобилям.
     */
    private final int MAX_POWER = 300;
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
     * Конструктор класса GetPowerfulCar.
     * <p>
     * Инициализирует поля объекта:
     * - модель — случайным образом выбирается на основе PowerfulCarData;
     * - год — случайный в диапазоне от 1990 до 2024;
     * - мощность — случайная в диапазоне от MIN_POWER до MAX_POWER.
     */
    public GetPowerfulCar() {
        this.model = DataGeneration.randomModel(new PowerfulCarData());
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
     * для автомобилей с высокой мощностью.
     * <p>
     * Содержит специфичные версии моделей Audi, Toyota, Ford и BMW, соответствующие категории высокой мощности.
     */
    class PowerfulCarData extends GetDataCar {
        /**
         * Конструктор инициализирует массивы моделей для каждого производителя,
         * включая пробелы в начале — как часть форматирования полного названия.
         */
        PowerfulCarData() {
            AUDI = new String[]{" A5", " Q7", " Q5", " A8", " A7"};
            TOYOTA = new String[]{" Camry", " Highlander", " RAV4", " Land Cruiser"};
            FORD = new String[]{" KUGA", " ECOBOOST", " FORD EXPLORER VI"};
            BMW = new String[]{"X3 G01", " 4", " X5 G05", " 7"};
        }
    }
}

