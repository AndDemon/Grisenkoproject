package org.example;
import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Створення списку для зберігання об'єктів
        List<Calc> calculations = new LinkedList<>();
        // Створення об'єктів та передача їм параметрів
        Solv solver1 = new Solv(60, 3);
        Solv solver2 = new Solv(23, 34);

        solver1.solve();
        solver2.solve();

        calculations.add(solver1.getData());
        calculations.add(solver2.getData());
        // Серіалізація списку
        saveObject(calculations, "data_collection.ser");

        // Десеріалізація об'єктів
        List<Calc> restoredCalculations = loadObject("data_collection.ser");
        // Виведення параметрів та результатів
        for (Calc restoredData : restoredCalculations) {
            restoredData.displayParameters(restoredData);
            restoredData.displayResult(restoredData);
            System.out.println("--------");
        }
    }
    // Метод для серіалізації списку
    private static void saveObject(List<Calc> data, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Метод для десеріалізації
    private static List<Calc> loadObject(String fileName) {
        List<Calc> data = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            data = (List<Calc>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}