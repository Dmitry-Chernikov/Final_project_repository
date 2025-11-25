package org.aston.app.util;

import org.aston.app.model.Car;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Collection;

/**
 * Дополнительное задание 3*: кастомная коллекция на основе массива
 */

/**
 * Кастомная реализация коллекции автомобилей на основе массива.
 * <p>
 * Класс расширяет {@link AbstractList}, предоставляя базовую функциональность списка
 * и реализуя методы добавления, удаления, получения элементов и другие стандартные операции.
 * <p>
 * Внутреннее хранилище автоматически расширяется при необходимости.
 */
public class CustomCarCollection extends AbstractList<Car> {

    /**
     * Внутренний массив для хранения объектов Car.
     */
    private Car[] values;

    /**
     * Текущее количество элементов в коллекции.
     */
    private int size;

    /**
     * Стандартная начальная ёмкость коллекции при создании без указания размера.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор по умолчанию.
     * <p>
     * Создаёт внутренний массив ёмкостью по умолчанию (10 элементов).
     */
    public CustomCarCollection() {
        values = new Car[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор с указанием начальной ёмкости.
     *
     * @param initialCapacity начальная ёмкость внутреннего массива
     * @throws IllegalArgumentException если указанная ёмкость меньше или равна нулю
     */
    public CustomCarCollection(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        values = new Car[initialCapacity];
    }

    /**
     * Добавляет указанный автомобиль в конец коллекции.
     * <p>
     * Если текущий массив заполнен, происходит автоматическое расширение.
     *
     * @param value объект Car, который необходимо добавить
     * @return всегда возвращает true (в соответствии с контрактом Collection)
     */
    public boolean add(Car value) {
        resize();
        values[size++] = value;
        return true;
    }

    /**
     * Возвращает автомобиль по указанному индексу.
     *
     * @param index позиция элемента в коллекции (от 0 до size - 1)
     * @return объект Car, находящийся по указанному индексу
     * @throws IndexOutOfBoundsException если индекс выходит за допустимые границы
     */
    public Car get(int index) {
        checkIndex(index);
        return values[index];
    }

    /**
     * Удаляет автомобиль по указанному индексу.
     *
     * @param index позиция элемента, который необходимо удалить
     * @return удалённый объект Car
     * @throws IndexOutOfBoundsException если индекс выходит за допустимые границы
     */
    public Car remove(int index) {
        checkIndex(index);
        Car removedValue = values[index];
        System.arraycopy(values, index + 1, values, index, values.length - index - 1);
        values[--size] = null;
        return removedValue;
    }

    /**
     * Добавляет все автомобили из указанной коллекции в текущую.
     *
     * @param other коллекция, элементы которой нужно добавить
     * @return всегда возвращает true, если коллекция была изменена
     * @throws NullPointerException если переданная коллекция равна null
     */
    public boolean addAll(Collection<? extends Car> other) {
        Car[] incoming = other.toArray(Car[]::new);
        resize(size + incoming.length);
        System.arraycopy(incoming, 0, values, size, incoming.length);
        size += incoming.length;
        return true;
    }

    /**
     * Возвращает текущее количество элементов в коллекции.
     *
     * @return количество автомобилей в коллекции
     */
    public int size() {
        return size;
    }

    /**
     * Увеличивает ёмкость внутреннего массива, если требуется.
     *
     * @param i минимально требуемая ёмкость
     */
    private void resize(int i) {
        if (i > values.length) {
            int newCapacity = Math.max(i, values.length * 2);
            var newValues = new Car[newCapacity];
            System.arraycopy(values, 0, newValues, 0, size);
            values = newValues;
        }
    }

    /**
     * Увеличивает ёмкость массива, если текущего места недостаточно для добавления одного элемента.
     */
    private void resize() {
        resize(size + 1);
    }

    /**
     * Проверяет корректность индекса.
     *
     * @param index индекс, который необходимо проверить
     * @throws IndexOutOfBoundsException если индекс отрицательный или больше/равен size
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", size: " + size);
        }
    }

    /**
     * Возвращает массив, содержащий все элементы коллекции.
     * <p>
     * Возвращает внутренний массив целиком (включая null-элементы после size).
     * Для корректного копирования следует использовать {@link Arrays#copyOf(Object[], int)}.
     *
     * @return внутренний массив значений
     */
    @Override
    public Car[] toArray() {
        return values;
    }
}
