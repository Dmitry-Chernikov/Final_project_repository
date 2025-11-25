package org.aston.app.strategy;

import org.aston.app.model.Car;

/**
 * Create by dmitry on 23.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 23.11.2025
 * @project : org.aston.final.project
 * Class SortByAllFilesComparable
 */

/**
 * Класс SortByAllFilesComparable реализует интерфейс SortStrategy и предназначен для сортировки
 * массива объектов Car по всем полям одновременно, используя естественный порядок сравнения.
 * <p>
 * Для сравнения объектов Car используется реализация метода compareTo в классе Car.
 * Алгоритм сортировки — быстрая сортировка (quicksort).
 */
public class SortByAllFilesComparable implements SortStrategy<Car> {

    /**
     * Выполняет сортировку массива автомобилей с использованием естественного порядка сравнения.
     * <p>
     * Использует алгоритм быстрой сортировки, начиная с первого (0)
     * и последнего (length - 1) индексов массива.
     *
     * @param cars массив объектов Car, который необходимо отсортировать по всем полям
     */
    @Override
    public void sort(Car[] cars) {
        quickSort(cars, 0, cars.length - 1);
    }

    /**
     * Выполняет разделение подмассива cars[low..high] на две части относительно опорного элемента.
     * <p>
     * В качестве опорного выбирается последний элемент подмассива (по индексу high).
     * Все элементы, которые меньше или равны опорному (согласно методу compareTo), перемещаются в левую часть.
     *
     * @param cars массив объектов Car, подлежащий разделению
     * @param low  начальный индекс подмассива
     * @param high конечный индекс подмассива
     * @return индекс опорного элемента после завершения разделения
     */
    @Override
    public int partition(Car[] cars, int low, int high) {
        Car pivot = cars[high]; // Опорный элемент — последний
        int i = low - 1; // Индекс меньшего элемента

        for (int j = low; j < high; j++) {
            if (cars[j].compareTo(pivot)  <= 0) { // Если текущий элемент меньше или равен опорному
                i++;
                swap(cars, i, j); // Меняем местами элементы
            }
        }
        swap(cars, i + 1, high); // Меняем местами опорный элемент с элементом на позиции i+1
        return i + 1;
    }
}
