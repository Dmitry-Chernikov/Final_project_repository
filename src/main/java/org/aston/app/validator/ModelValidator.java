package org.aston.app.validator;

import org.aston.app.exception.CarValidationException;
import org.aston.app.model.Car;

public class ModelValidator implements CarValidator {
    @Override
    public void validate(Car car) {
        if ((car.getModel() == null) || car.getModel().trim().isEmpty()) {
            throw new CarValidationException("Model is required");
        }
    }
}
