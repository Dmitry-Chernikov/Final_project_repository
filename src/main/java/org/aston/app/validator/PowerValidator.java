package org.aston.app.validator;

import org.aston.app.exception.CarValidationException;
import org.aston.app.model.Car;

public class PowerValidator implements CarValidator {
    @Override
    public void validate(Car car) {
        if (car.getPower() <= 0) {
            throw new CarValidationException("Horsepower must be positive - Horsepower is required" );
        }
    }
}
