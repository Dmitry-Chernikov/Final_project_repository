package org.aston.app.util;

import org.aston.app.model.Car;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomCarCollectionTest {

    @Test
    void testAddAll() {
        CustomCarCollection cars = new CustomCarCollection(2);
        cars.add(new Car
            .Builder()
            .setPower(100)
            .setModel("Model A")
            .setYear(1990)
            .build()
        );
        cars.addAll(
            List.of(
                new Car
                    .Builder()
                    .setPower(110)
                    .setModel("Model B")
                    .setYear(1991)
                    .build(),
                new Car
                    .Builder()
                    .setPower(120)
                    .setModel("Model C")
                    .setYear(1991)
                    .build()
            )
        );
        assertEquals(3, cars.size());
        assertEquals(new Car
            .Builder()
            .setPower(120)
            .setModel("Model C")
            .setYear(1991)
            .build(),
            cars.get(2)
        );
    }

    @Test
    void testRemove() {
        CustomCarCollection cars = new CustomCarCollection();
        cars.addAll(
            List.of(
                new Car
                    .Builder()
                    .setPower(100)
                    .setModel("Model A")
                    .setYear(1990)
                    .build(),
                new Car
                    .Builder()
                    .setPower(110)
                    .setModel("Model B")
                    .setYear(1991)
                    .build(),
                new Car
                    .Builder()
                    .setPower(120)
                    .setModel("Model C")
                    .setYear(1991)
                    .build(),
                new Car
                    .Builder()
                    .setPower(130)
                    .setModel("Model D")
                    .setYear(1993)
                    .build()
            )
        );
        cars.remove(1);
        assertEquals(3, cars.size());
        assertEquals(new Car
            .Builder()
            .setPower(110)
            .setModel("Model B")
            .setYear(1991)
            .build(),
            cars.get(1)
        );
    }

    @Test
    void testConstructorWithNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> new CustomCarCollection(-5));
    }

    @Test
    void testGetInvalidIndex() {
        CustomCarCollection cars = new CustomCarCollection();
        cars.add(new Car
            .Builder()
            .setPower(100)
            .setModel("Model A")
            .setYear(1991)
            .build()
        );
        assertThrows(IndexOutOfBoundsException.class, () -> cars.get(1));
        assertThrows(IndexOutOfBoundsException.class, () -> cars.get(-1));
    }

    @Test
    void testRemoveInvalidIndex() {
        CustomCarCollection cars = new CustomCarCollection();
        cars.add(new Car
            .Builder()
            .setPower(100)
            .setModel("Model A")
            .setYear(1990)
            .build()
        );
        assertThrows(IndexOutOfBoundsException.class, () -> cars.remove(1));
        assertThrows(IndexOutOfBoundsException.class, () -> cars.remove(-1));
    }

    @Test
    void testRemoveLastElement() {
        CustomCarCollection cars = new CustomCarCollection();
        cars.addAll(
            List.of(new Car
                    .Builder()
                    .setPower(100)
                    .setModel("Model A")
                    .setYear(1990)
                    .build(),
                new Car
                    .Builder()
                    .setPower(110)
                    .setModel("Model B")
                    .setYear(1991)
                    .build(),
                new Car
                    .Builder()
                    .setPower(120)
                    .setModel("Model C")
                    .setYear(1991)
                    .build()
            )
        );
        var removed = cars.remove(2);
        assertEquals(new Car
            .Builder()
            .setPower(120)
            .setModel("Model C")
            .setYear(1991)
            .build(),
            removed
        );
        assertEquals(2, cars.size());
        assertThrows(IndexOutOfBoundsException.class, () -> cars.get(2));
    }

    @Test
    void testNullElements() {
        CustomCarCollection cars = new CustomCarCollection();
        cars.add(null);
        assertNull(cars.getFirst());
        assertEquals(1, cars.size());
    }

}