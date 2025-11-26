package org.aston.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

public class FileService {
    
    public static <T> void writeSortedCollection(Collection<T> collection, String filename, Comparator<T> comparator) {
        try {
            List<T> sortedList = new ArrayList<>(collection);
            if (comparator != null) {
                sortedList.sort(comparator);
            } else if (!sortedList.isEmpty()) {
                try {
                    @SuppressWarnings("unchecked")
                    List<Comparable<Object>> comparableList = (List<Comparable<Object>>) (List<?>) sortedList;
                    Collections.sort(comparableList);
                } catch (ClassCastException e) {
                    System.out.println("Элементы не поддерживают сортировку, записываем без сортировки");
                }
            }
            
            List<String> lines = sortedList.stream()
                                         .map(Object::toString)
                                         .collect(Collectors.toList());
            
            Path filePath = Paths.get(filename);
            Files.write(filePath, lines, 
                      StandardOpenOption.CREATE, 
                      StandardOpenOption.APPEND);
            
            System.out.println("Отсортированная коллекция записана в файл: " + filename);
            System.out.println("Записано элементов: " + lines.size());
            
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }
    
    public static void writeFoundValues(List<?> values, String filename) {
        try {
            List<String> lines = values.stream()
                                     .map(Object::toString)
                                     .collect(Collectors.toList());
            
            Path filePath = Paths.get(filename);
            Files.write(filePath, lines,
                      StandardOpenOption.CREATE,
                      StandardOpenOption.APPEND);
            
            System.out.println("Найденные значения записаны в файл: " + filename);
            System.out.println("Записано элементов: " + lines.size());
            
        } catch (IOException e) {
            System.err.println("Ошибка при записи найденных значений: " + e.getMessage());
        }
    }
    
    public static void writeMessage(String message, String filename) {
        try {
            Path filePath = Paths.get(filename);
            Files.write(filePath, Collections.singletonList(message),
                      StandardOpenOption.CREATE,
                      StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Ошибка при записи сообщения: " + e.getMessage());
        }
    }
}