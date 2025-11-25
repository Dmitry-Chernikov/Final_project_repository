package org.aston.app.model;

import java.util.Comparator;

/**
 * Create by dmitry on 20.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 20.11.2025
 * @project : org.aston.final.project
 * Class CarYearComparator
 */

/**
 * Класс CarYearComparator реализует интерфейс Comparator и предназначен для сравнения
 * объектов Car по году выпуска в порядке возрастания.
 * <p>
 * Используется для сортировки автомобилей по полю year с помощью компаратора.
 */
public class CarYearComparator implements Comparator<Car> {

    /**
     * Сравнивает два объекта Car по году выпуска.
     *
     * @param o1 первый объект Car
     * @param o2 второй объект Car
     * @return отрицательное значение, если год o1 меньше года o2;
     *         положительное значение, если год o1 больше года o2;
     *         ноль, если года равны
     */
    @Override
    public int compare(Car o1, Car o2) {
        return ((Integer)o1.getYear()).compareTo(o2.getYear());
    }
}
