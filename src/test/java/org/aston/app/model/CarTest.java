package org.aston.app.model;

import org.aston.app.exception.CarValidationException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void buildCarSuccessfully() {
        Car car = new Car.Builder()
            .setPower(150)
            .setModel("ModelName")
            .setYear(2020)
            .build();

        assertEquals(150, car.getPower());
        assertEquals("ModelName", car.getModel());
        assertEquals(2020, car.getYear());
    }

    @Test
    void equalsShouldReturnTrueForSameValues() {
        Car c1 = new Car.Builder()
            .setPower(100)
            .setModel("ModelName")
            .setYear(2018)
            .build();

        Car c2 = new Car.Builder()
            .setPower(100)
            .setModel("ModelName")
            .setYear(2018)
            .build();

        assertEquals(c1, c2);
        assertEquals(c1.hashCode(), c2.hashCode());
    }

    @Test
    void toStringShouldContainFields() {
        Car car = new Car.Builder()
            .setPower(200)
            .setModel("ModelName")
            .setYear(2021)
            .build();

        String s = car.toString();

        assertTrue(s.contains("horse power=200"));
        assertTrue(s.contains("model name='ModelName'"));
        assertTrue(s.contains("2021"));
    }

    @Test
    void failWhenHorsePowerIsEmpty() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setModel("ModelName")
                .setYear(2020)
                .build()
        );
    }

    @Test
    void failWhenModelNameIsEmpty() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setPower(120)
                .setYear(2020)
                .build()
        );
    }

    @Test
    void failWhenProductionYearIsEmpty() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setPower(120)
                .setModel("ModelName")
                .build()
        );
    }

    @Test
    void failWhenProductionYearInvalid() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setPower(120)
                .setModel("ModelName")
                .setYear(1700)
                .build()
        );
    }

    @Test
    void failWhenHorsePowerIsZero() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setPower(0)
                .setModel("ModelName")
                .setYear(2020)
                .build()
        );
    }

    @Test
    void buildShouldFailWhenModelNameIsWhitespaces() {
        assertThrows(CarValidationException.class, () ->
            new Car.Builder()
                .setPower(120)
                .setModel(" ")
                .setYear(2020)
                .build()
        );
    }

    @Test
    void testCarNaturalOrdering() {
        Car c1 = new Car.Builder().setModel("ModelName1").setPower(150).setYear(2010).build();
        Car c2 = new Car.Builder().setModel("ModelName2").setPower(120).setYear(2010).build();
        Car c3 = new Car.Builder().setModel("ModelName3").setPower(200).setYear(2015).build();
        Car c4 = new Car.Builder().setModel("ModelName2").setPower(100).setYear(2005).build();
        Car c5 = new Car.Builder().setModel("ModelName5").setPower(120).setYear(2005).build();

        var list = new ArrayList<>(List.of(c1, c2, c3, c4, c5));

        list.sort(null);

        assertEquals(List.of(c4, c5, c2, c1, c3), list);
    }
}