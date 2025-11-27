package org.aston.app.strategy;

import org.aston.app.model.Car;
import org.aston.app.model.CarModelComparator;
import org.aston.app.model.CarPowerComparator;
import org.aston.app.model.CarYearComparator;

import java.util.Comparator;

/**
 * Класс SortByAllFieldsComparator реализует интерфейс SortStrategy и предназначен для сортировки
 * массива объектов Car по всем полям одновременно, используя композицию компараторов.
 * <p>
 * Порядок сортировки: сначала по модели (лексикографически), затем по мощности (возрастание),
 * затем по году выпуска (возрастание). Использует алгоритм быстрой сортировки (quicksort).
 */
public class SortByAllFieldsComparator implements SortStrategy<Car> {

    /**
     * Композиция компараторов, определяющая общий порядок сравнения объектов Car.
     * Сначала сравниваются модели, затем — мощность, затем — год выпуска.
     */
    private Comparator<Car> totalComparator = new CarModelComparator().thenComparing(new CarPowerComparator()).thenComparing(new CarYearComparator());

    /**
     * Выполняет сортировку массива автомобилей по всем полям с использованием композиции компараторов.
     * <p>
     * Использует алгоритм быстрой сортировки, начиная с первого (0)
     * и последнего (length - 1) индексов массива.
     *
     * @param cars массив объектов Car, который необходимо отсортировать
     */
    @Override
    public void sort(Car[] cars) {
        quickSort(cars, 0, cars.length - 1);
    }

    /**
     * Выполняет разделение подмассива cars[low..high] на две части относительно опорного элемента.
     * <p>
     * В качестве опорного выбирается последний элемент подмассива (по индексу high).
     * Все элементы, которые меньше или равны опорному (согласно totalComparator), перемещаются в левую часть.
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
            if (totalComparator.compare(cars[j], pivot)  <= 0) { // Если текущий элемент меньше или равен опорному
                i++;
                swap(cars, i, j); // Меняем местами элементы
            }
        }
        swap(cars, i + 1, high); // Меняем местами опорный элемент с элементом на позиции i+1
        return i + 1;
    }
}
