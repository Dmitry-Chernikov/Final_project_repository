package org.aston.app.test;

/**
 * Create by dmitry on 17.11.2025
 *
 * @author : Dmitry Chernikov
 * @date : 17.11.2025
 * @project : org.aston.final.project
 * Class TestUtils
 */

/**
 * Вспомогательные методы для ручного тестирования
 */
public class TestUtils {
    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            System.err.println("FAIL: " + message);
        } else {
            System.out.println("OK: " + message);
        }
    }

    public static void assertEquals(String message, Object expected, Object actual) {
        assertTrue(message + " (expected: " + expected + ", actual: " + actual + ")", expected.equals(actual));
    }

    public static void assertEquals(String message, int expected, int actual) {
        assertTrue(message + " (expected: " + expected + ", actual: " + actual + ")", expected == actual);
    }

    public static void assertFalse(String message, boolean condition) {
        if (condition) {
            System.err.println("FAIL: " + message);
        } else {
            System.out.println("OK: " + message);
        }
    }
}
