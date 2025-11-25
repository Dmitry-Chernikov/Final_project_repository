package org.aston.app.exception;


/**
 * Исключение, выбрасываемое при неудачной валидации объекта Car.
 * <p>
 * Наследуется от RuntimeException, что делает его непроверяемым исключением.
 * Используется для сигнализации о проблемах с данными при создании автомобиля,
 * таких как неверный формат модели, недопустимый год выпуска или некорректная мощность.
 */
public class CarValidationException extends RuntimeException {
    /**
     * Конструктор с сообщением об ошибке.
     *
     * @param message текстовое описание причины исключения
     */
    public CarValidationException(String message) {
        super(message);
    }
}