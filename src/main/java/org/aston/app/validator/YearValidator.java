package org.aston.app.validator;

import org.aston.app.exception.CarValidationException;
import org.aston.app.model.Car;

import static java.time.Year.now;

/**
 * Класс YearValidator реализует интерфейс CarValidator и предназначен для валидации
 * поля года выпуска автомобиля (year) в объекте Car.
 * <p>
 * Проверяет, что год не равен нулю и находится в допустимом диапазоне —
 * от минимального значения (1885, год первого автомобиля) до следующего года после текущего.
 * Это позволяет принимать автомобили будущего года (например, при предзаказе).
 */
public class YearValidator implements CarValidator {

    /**
     * Минимальный допустимый год выпуска автомобиля.
     * Принято, что первый автомобиль был изобретён в 1885 году.
     */
    private static final int MIN_YEAR = 1885;

    /**
     * Максимальный допустимый год выпуска.
     * Устанавливается как текущий год + 1, чтобы разрешить указание автомобилей следующего года.
     */
    private static final int MAX_YEAR = now().getValue() + 1;

    /**
     * Выполняет валидацию года выпуска автомобиля.
     *
     * @param car объект автомобиля, поле year которого необходимо проверить
     * @throws CarValidationException если год равен 0 или выходит за допустимые границы
     */
    @Override
    public void validate(Car car) {
        if (car.getYear() == 0) {
            throw new CarValidationException("Year is required");
        }
        if (car.getYear() < MIN_YEAR || car.getYear() > MAX_YEAR) {
            throw new CarValidationException("Invalid year: " + car.getYear());
        }
    }
}
