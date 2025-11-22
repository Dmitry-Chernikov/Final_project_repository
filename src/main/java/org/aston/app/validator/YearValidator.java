package org.aston.app.validator;

import org.aston.app.exception.CarValidationException;
import org.aston.app.model.Car;

import static java.time.Year.now;

public class YearValidator implements CarValidator {

    private static final int MIN_YEAR = 1885;
    private static final int MAX_YEAR = now().getValue() + 1;

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
