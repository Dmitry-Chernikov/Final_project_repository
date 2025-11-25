package org.aston.app.validator;

import org.aston.app.exception.CarValidationException;
import org.aston.app.model.Car;

/**
 * Класс PowerValidator реализует интерфейс CarValidator и предназначен для валидации
 * поля мощности автомобиля (power, л.с.) в объекте Car.
 * <p>
 * Проверяет, что значение мощности является положительным числом. Нулевое или отрицательное
 * значение недопустимо, так как мощность двигателя должна быть строго больше нуля.
 */
public class PowerValidator implements CarValidator {

    /**
     * Выполняет валидацию мощности автомобиля.
     *
     * @param car объект автомобиля, поле power которого необходимо проверить
     * @throws CarValidationException если мощность меньше или равна нулю
     */
    @Override
    public void validate(Car car) {
        if (car.getPower() <= 0) {
            throw new CarValidationException("Horsepower must be positive - Horsepower is required" );
        }
    }
}
