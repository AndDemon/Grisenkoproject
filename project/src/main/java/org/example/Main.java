package org.example;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        // Створення Problem_Solver
        Problem_Solver solver = new Problem_Solver(60, 23);

        // Виклик методу solve
        solver.solve();

        // Серіалізація
        serializeObject(solver.getCalculationData(), "data.ser");

        // Десеріалізація
        Calc restoredData = deserializeObject("data.ser");

        // Вивід результатів
        System.out.println("Параметр 1: " + restoredData.getParameter1());
        System.out.println("Параметр 2: " + restoredData.getParameter2());
        System.out.println("Результат: " + restoredData.getResult());
    }

    private static void serializeObject(Calc data, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Calc deserializeObject(String fileName) {
        Calc data = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            data = (Calc) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
