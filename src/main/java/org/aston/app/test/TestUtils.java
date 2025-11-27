package org.aston.app.test;

/**
 * Класс TestUtils предоставляет простые утилиты для выполнения базовых проверок в тестах.
 * <p>
 * Содержит методы для проверки условий, равенства значений и логических выражений.
 * Результаты проверок выводятся в консоль: "OK" — если проверка пройдена, "FAIL" — если нет.
 * <p>
 * Используется как замена стандартным библиотекам тестирования (например, JUnit),
 * поскольку в задании указано, что можно обойтись без них.
 */
public class TestUtils {
    /**
     * Проверяет, что условие истинно.
     * <p>
     * Если условие ложно, выводит сообщение об ошибке в System.err.
     * Если истинно — выводит успешное сообщение в System.out.
     *
     * @param message   сообщение, поясняющее суть проверки
     * @param condition проверяемое условие
     */
    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            System.err.println("FAIL: " + message);
        } else {
            System.out.println("OK: " + message);
        }
    }

    /**
     * Проверяет, что ожидаемый объект равен фактическому.
     * <p>
     * Использует переопределённый метод equals для сравнения объектов.
     * Выводит сообщение с подробностями (ожидаемое и фактическое значение) при несовпадении.
     *
     * @param message  сообщение, поясняющее суть проверки
     * @param expected ожидаемое значение
     * @param actual   фактическое значение
     */
    public static void assertEquals(String message, Object expected, Object actual) {
        assertTrue(message + " (expected: " + expected + ", actual: " + actual + ")", expected.equals(actual));
    }

    /**
     * Проверяет, что ожидаемое целое число равно фактическому.
     * <p>
     * Так как int — примитив, используется оператор ==.
     * Выводит сообщение с подробностями при несовпадении.
     *
     * @param message сообщение, поясняющее суть проверки
     * @param expected ожидаемое значение
     * @param actual фактическое значение
     */
    public static void assertEquals(String message, int expected, int actual) {
        assertTrue(message + " (expected: " + expected + ", actual: " + actual + ")", expected == actual);
    }

    /**
     * Проверяет, что условие ложно.
     * <p>
     * Если условие истинно, выводит сообщение об ошибке в System.err.
     * Если ложно — выводит успешное сообщение в System.out.
     *
     * @param message сообщение, поясняющее суть проверки
     * @param condition проверяемое условие
     */
    public static void assertFalse(String message, boolean condition) {
        if (condition) {
            System.err.println("FAIL: " + message);
        } else {
            System.out.println("OK: " + message);
        }
    }
}
