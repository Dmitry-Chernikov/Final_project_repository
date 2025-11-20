package org.aston.app.model;

import java.util.Comparator;

/**
 * Create by dmitry on 20.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 20.11.2025
 * @project : org.aston.final.project
 * Class CarPowerComparator
 */
public class CarPowerComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return ((Integer)o1.getPower()).compareTo(o2.getPower());
    }
}

