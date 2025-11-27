package org.aston.app.random;

import org.aston.app.model.Car;

/**
 * Класс GetSportCar реализует интерфейс RandomGeneration и предназначен для создания объектов Car
 * с высокой спортивной мощностью двигателя (в диапазоне от 300 до 600 л.с.).
 * <p>
 * Использует внутренние данные о моделях спортивных автомобилей с ограниченным набором модификаций
 * и генерирует случайные значения в заданных пределах.
 */
public class GetSportCar implements RandomGeneration {

    /**
     * Минимальная мощность, присваиваемая генерируемым спортивным автомобилям.
     */
    private final int MIN_POWER = 300;

    /**
     * Максимальная мощность, присваиваемая генерируемым спортивным автомобилям.
     */
    private final int MAX_POWER = 600;
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
     * Конструктор класса GetSportCar.
     * <p>
     * Инициализирует поля объекта:
     * - модель — случайным образом выбирается на основе SportCarData;
     * - год — случайный в диапазоне от 1990 до 2024;
     * - мощность — случайная в диапазоне от MIN_POWER до MAX_POWER.
     */
    public GetSportCar() {
        this.model = DataGeneration.randomModel(new SportCarData());
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
     * для спортивных автомобилей.
     * <p>
     * Содержит специфичные версии моделей Audi, Toyota, Ford и BMW, соответствующие категории спортивных автомобилей.
     */
    class SportCarData extends GetDataCar {
        /**
         * Конструктор инициализирует массивы моделей для каждого производителя,
         * включая пробелы в начале — как часть форматирования полного названия.
         */
        SportCarData() {
            AUDI = new String[]{" R8", " RS"};
            TOYOTA = new String[]{" GR Supra A90", " GR Corolla"};
            FORD = new String[]{" Mustang Shelby GT500", " GT", " MUSTANG DARK HORSE"};
            BMW = new String[]{" M2", " M4", " M8", " X2", " X4", " X5", " X6"};
        }
    }
}
