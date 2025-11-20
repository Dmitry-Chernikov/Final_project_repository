package org.aston.app.util;

import org.aston.app.model.Car;

import java.util.AbstractList;
import java.util.Collection;

/**
 * Дополнительное задание 3*: кастомная коллекция на основе массива
 */
public class CustomCarCollection extends AbstractList<Car> {

    private Car[] values;
    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public CustomCarCollection() {
        values = new Car[DEFAULT_CAPACITY];
    }

    public CustomCarCollection(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        values = new Car[initialCapacity];
    }

    public boolean add(Car value) {
        resize();
        values[size++] = value;
        return true;
    }

    public Car get(int index) {
        checkIndex(index);
        return values[index];
    }

    public Car remove(int index) {
        checkIndex(index);
        Car removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, values.length - index - 1);
        values[--size] = null;
        return removedValue;
    }

    public boolean addAll(Collection<? extends Car> other) {
        Car[] incoming = other.toArray(Car[]::new);
        resize(size + incoming.length);
        System.arraycopy(incoming, 0, values, size, incoming.length);
        size += incoming.length;
        return true;
    }

    public int size() {
        return size;
    }

    private void resize(int i) {
        if (i > values.length) {
            int newCapacity = Math.max(i, values.length * 2);
            var newValues = new Car[newCapacity];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }

    private void resize() {
        resize(size + 1);
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }
}
