package org.aston.app.model;

import java.util.Comparator;

/**
 * Класс CarPowerComparator реализует интерфейс Comparator и предназначен для сравнения
 * объектов Car по мощности двигателя в порядке возрастания.
 * <p>
 * Используется для сортировки автомобилей по полю power с помощью компаратора.
 */
public class CarPowerComparator implements Comparator<Car> {

    /**
     * Сравнивает два объекта Car по мощности двигателя.
     *
     * @param o1 первый объект Car
     * @param o2 второй объект Car
     * @return отрицательное значение, если мощность o1 меньше мощности o2;
     *         положительное значение, если мощность o1 больше мощности o2;
     *         ноль, если мощности равны
     */
    @Override
    public int compare(Car o1, Car o2) {
        return ((Integer)o1.getPower()).compareTo(o2.getPower());
    }
}

