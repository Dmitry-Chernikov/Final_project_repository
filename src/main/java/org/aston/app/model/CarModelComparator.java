package org.aston.app.model;

import java.util.Comparator;

/**
 * Класс CarModelComparator реализует интерфейс Comparator и предназначен для сравнения
 * объектов Car по модели в лексикографическом порядке.
 * <p>
 * Используется для сортировки автомобилей по полю model с помощью компаратора.
 */
public class CarModelComparator implements Comparator<Car> {

    /**
     * Сравнивает два объекта Car по названию модели.
     *
     * @param o1 первый объект Car
     * @param o2 второй объект Car
     * @return отрицательное значение, если модель o1 лексикографически меньше модели o2;
     *         положительное значение, если модель o1 лексикографически больше модели o2;
     *         ноль, если модели равны
     */
    @Override
    public int compare(Car o1, Car o2) {
        return o1.getModel().compareTo(o2.getModel());
    }
}
