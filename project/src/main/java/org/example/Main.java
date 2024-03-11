package org.example;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Створюємо об'єкт для обчислень
        Solv solver = new Solv();
        solver.calculateAreas();


        TableDisplay tableDisplay = chooseTableDisplay();
        tableDisplay.displayTable(solver.getData(), new String[]{"Triangle Area", "Square Area", "Total Area"});

        // Серіалізуємо та зберігаємо дані
        saveObject(solver.getData(), "calculation_data.ser");

        // Десеріалізуємо та виводимо збережені дані
        Calc loadedData = loadObject("calculation_data.ser");
        if (loadedData != null) {
            System.out.println("\nЗбережені дані:");
            tableDisplay.displayTable(loadedData, new String[]{"Triangle Area", "Square Area", "Total Area"});
        }


        executeChangeParamsCommand(solver);


        undoLastCommand(solver);

        // Виводимо оновлені результати
        tableDisplay.displayTable(solver.getData(), new String[]{"Triangle Area", "Square Area", "Total Area"});
    }

    // Метод для вибору засобу виведення таблиці
    private static TableDisplay chooseTableDisplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Оберіть тип таблиці:");
        System.out.println("1. Простий вигляд таблиці");
        System.out.println("2. Вигляд таблиці з оформленням");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return new SimpleTableDisplay();
            case 2:
                return new FancyTableDisplay();
            default:
                System.out.println("Невірний вибір. Використано простий вигляд таблиці за замовчуванням.");
                return new SimpleTableDisplay();
        }
    }


    private static void saveObject(Calc data, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
            System.out.println("\nДані успішно збережено.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Calc loadObject(String fileName) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            Calc data = (Calc) inputStream.readObject();
            System.out.println("\nДані успішно завантажено.");
            return data;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    private static void executeChangeParamsCommand(Solv solver) {
        CommandManager commandManager = CommandManager.getInstance();
        double newSideLength = 10.0; // Замініть на необхідне значення нової довжини сторони

        // Виконуємо команду ChangeParamsCommand
        ChangeParamsCommand changeParamsCommand = new ChangeParamsCommand(solver, newSideLength);
        commandManager.executeCommand(changeParamsCommand);
    }


    private static void undoLastCommand(Solv solver) {
        CommandManager commandManager = CommandManager.getInstance();
        commandManager.undoLastCommand();
    }
}
