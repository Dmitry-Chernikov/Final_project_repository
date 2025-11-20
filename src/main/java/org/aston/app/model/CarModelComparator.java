package org.aston.app.model;

import java.util.Comparator;

/**
 * Create by dmitry on 20.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 20.11.2025
 * @project : org.aston.final.project
 * Class CarModelComparator
 */
public class CarModelComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return o1.getModel().compareTo(o2.getModel());
    }
}
