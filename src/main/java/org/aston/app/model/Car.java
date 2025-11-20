package org.aston.app.model;

import org.aston.app.exception.CarValidationException;

import java.util.Objects;


/**
 * Класс, представляющий автомобиль.
 * Реализует интерфейс Comparable для возможности сортировки автомобилей.
 */
public class Car implements Comparable<Car> {
    private static final int MIN_YEAR = 1885;
    private static final int MAX_YEAR = java.time.Year.now().getValue() + 1;

    /**
     * Модель автомобиля. Не может быть null.
     */
    private final String model;

    /**
     * Мощность двигателя в лошадиных силах. Должна быть положительным числом.
     */
    private final int power;

    /**
     * Год производства автомобиля. Должен быть в допустимом диапазоне.
     */
    private final int year;

    /**
     * Приватный конструктор класса Car.
     * Используется билдером для создания экземпляра после валидации.
     *
     * @param model Модель автомобиля
     * @param power Мощность двигателя
     * @param year  Год производства
     */
    private Car(final String model, final int power, final int year) {
        this.model = model;
        this.power = power;
        this.year = year;
    }

    /**
     * Конструктор, принимающий экземпляр билдера.
     * Инициализирует объект Car с помощью данных из билдера.
     *
     * @param builder экземпляр Builder, содержащий данные для инициализации
     */
    public Car(Builder builder){
        this(builder.model, builder.power, builder.year);
    }

    /**
     * Возвращает модель автомобиля.
     *
     * @return Модель автомобиля
     */
    public String getModel() {
        return model;
    }

    /**
     * Возвращает мощность двигателя.
     *
     * @return Мощность в лошадиных силах
     */
    public int getPower() {
        return power;
    }

    /**
     * Возвращает год производства автомобиля.
     *
     * @return Год производства
     */
    public int getYear() {
        return year;
    }

    /**
     * Строитель (Builder) для создания объектов Car.
     * Позволяет пошагово создавать объект автомобиля с валидацией данных.
     */
    public static class Builder {
        /**
         * Мощность двигателя, устанавливаемая через билдер.
         */
        private int power;

        /**
         * Модель автомобиля, устанавливаемая через билдер.
         */
        private String model;

        /**
         * Год производства, устанавливаемый через билдер.
         */
        private int year;

        /**
         * Устанавливает мощность двигателя.
         *
         * @param power Мощность в лошадиных силах
         * @return Текущий экземпляр билдера
         */
        public Builder setPower(final int power) {
            this.power = power;
            return this;
        }

        /**
         * Устанавливает модель автомобиля.
         *
         * @param model Модель автомобиля
         * @return Текущий экземпляр билдера
         */
        public Builder setModel(final String model) {
            this.model = model;
            return this;
        }

        /**
         * Устанавливает год производства автомобиля.
         *
         * @param year Год производства
         * @return Текущий экземпляр билдера
         */
        public Builder setYear(final int year) {
            this.year = year;
            return this;
        }

        /**
         * Создает объект Car после валидации всех полей.
         *
         * @return Новый экземпляр Car
         * @throws CarValidationException если данные не проходят валидацию
         */
        public Car build() {
            validate();
            return new Car(model, power, year);
        }

        /**
         * Проверяет корректность введенных данных перед созданием объекта.
         * Валидация включает проверку на непустое значение модели,
         * положительную мощность и допустимый год производства.
         *
         * @throws CarValidationException если какое-либо поле не проходит валидацию
         */
        private void validate() {
            if ((model == null) || model.trim().isEmpty()) {
                throw new CarValidationException("Model is required");
            }
            if (power <= 0) {
                throw new CarValidationException("Horsepower must be positive - Horsepower is required" );
            }
            if (year == 0) {
                throw new CarValidationException("Year is required");
            }
            if (year < MIN_YEAR || year > MAX_YEAR) {
                throw new CarValidationException("Invalid year: " + year);
            }
        }
    }

    /**
     * Проверяет, равен ли текущий объект другому объекту.
     * Два автомобиля считаются равными, если у них совпадают модель, мощность и год производства.
     *
     * @param o Объект для сравнения
     * @return true, если объекты равны, иначе false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return power == car.power &&
            year == car.year &&
            model.equals(car.model);
    }

    /**
     * Возвращает хеш-код объекта на основе его полей.
     * Используется для эффективной работы хэш-таблиц.
     *
     * @return Хеш-код автомобиля
     */
    @Override
    public int hashCode() {
        return Objects.hash(power, model, year);
    }

    /**
     * Возвращает строковое представление объекта Car.
     * Предназначено для отладки и логирования.
     *
     * @return Строковое описание автомобиля
     */
    @Override
    public String toString() {
        return "Car{" +
            "horse power=" + power +
            ", model name='" + model + '\'' +
            ", production year=" + year +
            '}';
    }

    /**
     * Сравнивает текущий автомобиль с другим для упорядочивания.
     * Сначала сравнивается по мощности, затем по модели, затем по году производства.
     * Реализация необходима для использования в сортируемых коллекциях.
     *
     * @param other Другой автомобиль для сравнения
     * @return Отрицательное число, если этот автомобиль меньше other;
     *         положительное число, если больше; 0, если равны
     */
    @Override
    public int compareTo(Car other) throws NullPointerException {

        if (other == null) throw new NullPointerException("Cannot compare to null");

        int compare = this.model.compareTo(other.model);
        if (compare != 0) return compare;

        compare = Integer.compare(this.power, other.power);
        if (compare != 0) return compare;

        return Integer.compare(this.year, other.year);
    }
}
