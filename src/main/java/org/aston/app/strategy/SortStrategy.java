package org.aston.app.strategy;

import org.aston.app.model.Car;

/**
 * Create by dmitry on 16.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 16.11.2025
 * @project : org.aston.final.project
 * Interface SortStrategy
 */
public interface SortStrategy <T> {
    void sort(T[] cars);

    default void quickSort(T[] cars, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(cars, low, high); // Разделение на-под массивы и получение индекса опорного элемента
            quickSort(cars, low, pivotIndex - 1); // Сортировка левой части массива от low до pivotIndex - 1
            quickSort(cars, pivotIndex + 1, high); // Сортировка правой части массива от pivotIndex + 1 до high
        }
    }

    int partition(T[] cars, int low, int high);

    default void swap(T[] elements, int i, int j) {
        T temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
