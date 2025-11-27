package org.aston.app.random;

import org.aston.app.model.Car;

/**
 * Класс GetLowpowerCar реализует интерфейс RandomGeneration и предназначен для создания объектов Car
 * с низкой мощностью двигателя (в диапазоне от 87 до 110 л.с.).
 * <p>
 * Использует внутренние данные о моделях автомобилей с ограниченным набором модификаций
 * и генерирует случайные значения в заданных пределах.
 */
public class GetLowpowerCar implements RandomGeneration {

    /**
     * Минимальная мощность, присваиваемая генерируемым автомобилям.
     */
    private final int MIN_POWER = 87;

    /**
     * Максимальная мощность, присваиваемая генерируемым автомобилям.
     */
    private final int MAX_POWER = 110;
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
     * Конструктор класса GetLowpowerCar.
     * <p>
     * Инициализирует поля объекта:
     * - модель — случайным образом выбирается на основе LowPowerCarData;
     * - год — случайный в диапазоне от 1990 до 2024;
     * - мощность — случайная в диапазоне от MIN_POWER до MAX_POWER.
     */
    public GetLowpowerCar() {
        this.model = DataGeneration.randomModel(new LowPowerCarData());
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
     * для автомобилей с низкой мощностью.
     * <p>
     * Содержит специфичные версии моделей Audi, Toyota, Ford и BMW, соответствующие категории низкой мощности.
     */
    class LowPowerCarData extends GetDataCar {
        /**
         * Конструктор инициализирует массивы моделей для каждого производителя,
         * включая пробелы в начале — как часть форматирования полного названия.
         */
        LowPowerCarData() {
            AUDI = new String[]{" A3", " 100", " A4"};
            TOYOTA = new String[]{" Corolla", " Probox"};
            FORD = new String[]{" FUSION", " FIESTA", " FOCUS POWERSHIFT", " C-MAX 1.6 TDCI"};
            BMW = new String[]{" 1"};
        }
    }
}
