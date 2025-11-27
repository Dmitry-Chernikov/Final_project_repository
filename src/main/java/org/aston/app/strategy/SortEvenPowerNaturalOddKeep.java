package org.aston.app.strategy;

import org.aston.app.model.Car;

/**
 * Дополнительное задание 1: сортировка по полю power.
 * Четные значения сортируются по возрастанию, нечетные остаются на своих местах.
 * Класс SortEvenPowerNaturalOddKeep реализует интерфейс SortStrategy и предназначен для сортировки
 * массива объектов Car по следующему правилу:
 * - Автомобили с чётным значением мощности (power) сортируются по возрастанию.
 * - Автомобили с нечётным значением мощности остаются на своих исходных позициях.
 * <p>
 * Использует алгоритм быстрой сортировки (quicksort) для сортировки чётных элементов.
 */
public class SortEvenPowerNaturalOddKeep implements SortStrategy<Car> {

    /**
     * Выполняет сортировку массива автомобилей по описанному правилу:
     * чётные значения мощности сортируются по возрастанию, нечётные — не перемещаются.
     *
     * @param cars массив объектов Car, подлежащий частичной сортировке
     */
    @Override
    public void sort(Car[] cars) {
        // Извлекаем чётные значения мощности
        Car[] carEvenPowers = new Car[cars.length];
        int evenCount = 0;

        for (Car car : cars) {
            if (car.getPower() % 2 == 0) {
                carEvenPowers[evenCount++] = car;
            }
        }

        // Сортируем чётные значения
        quickSort(carEvenPowers, 0, evenCount - 1);

        // Возвращаем отсортированные чётные значения обратно в исходный массив
        int evenIndex = 0;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i].getPower() % 2 == 0) {
                int originalPower = cars[i].getPower();
                // Находим отсортированное значение
                Car carNewPower = carEvenPowers[evenIndex++];
                if (carNewPower.getPower() != originalPower) {
                    // Пересоздаём объект (необходимо, так как объекты Car неизменяемы)
                    cars[i] = carNewPower;
                }
            }
        }
    }

    /**
     * Выполняет разделение подмассива cars[low..high] относительно опорного элемента.
     * <p>
     * В качестве опорного используется последний элемент подмассива.
     * Все элементы, мощность которых меньше или равна опорной, перемещаются в левую часть.
     *
     * @param cars массив объектов Car, подлежащий разделению
     * @param low  начальный индекс подмассива
     * @param high конечный индекс подмассива
     * @return индекс опорного элемента после завершения разделения
     */
    @Override
    public int partition(Car[] cars, int low, int high) {
        Integer pivot = cars[high].getPower(); // Опорный элемент — последний
        int i = low - 1; // Индекс меньшего элемента

        for (int j = low; j < high; j++) {
            if (((Integer) cars[j].getPower()).compareTo(pivot) <= 0) { // Если текущий элемент меньше или равен опорному
                i++;
                swap(cars, i, j); // Меняем местами элементы
            }
        }
        swap(cars, i + 1, high); // Меняем местами опорный элемент с элементом на позиции i+1
        return i + 1;
    }
}
