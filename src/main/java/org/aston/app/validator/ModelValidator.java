package org.aston.app.validator;

import org.aston.app.exception.CarValidationException;
import org.aston.app.model.Car;

/**
 * Класс ModelValidator реализует интерфейс CarValidator и предназначен для валидации
 * поля модели автомобиля (model) в объекте Car.
 * <p>
 * Проверяет, что модель не является null и не состоит только из пробельных символов.
 * Пустая или неуказанная модель считается недопустимой.
 */
public class ModelValidator implements CarValidator {

    /**
     * Выполняет валидацию модели автомобиля.
     *
     * @param car объект автомобиля, поле model которого необходимо проверить
     * @throws CarValidationException если модель равна null или пустая (после удаления пробелов)
     */
    @Override
    public void validate(Car car) {
        if ((car.getModel() == null) || car.getModel().trim().isEmpty()) {
            throw new CarValidationException("Model is required");
        }
    }
}
