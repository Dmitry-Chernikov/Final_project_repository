package org.aston.app.ecxeption;

public class CarValidationException extends RuntimeException {
    public CarValidationException(String message) {
        super(message);
    }
}