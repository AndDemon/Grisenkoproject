package com.rx.javajxpr;

public class Main {
    public static void main(String[] args) {
        // Створюємо об'єкт для обчислень
        Solv solver = new Solv();
        solver.calculateAreas();

        // Вибираємо тип таблиці
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

        // Виконуємо команду зміни параметрів
        executeChangeParamsCommand(solver, 10.0);

        // Скасовуємо останню команду
        undoLastCommand(solver);

        // Виводимо оновлені результати
        tableDisplay.displayTable(solver.getData(), new String[]{"Triangle Area", "Square Area", "Total Area"});
    }

    private static TableDisplay chooseTableDisplay() {

        // Отримуємо тип таблиці від користувача або використовуємо значення за замовчуванням
    }

    private static void saveObject(Calc data, String fileName) {
        // Зберігаємо об'єкт в файл
    }

    private static Calc loadObject(String fileName) {
        // Завантажуємо об'єкт з файлу
    }

    private static void executeChangeParamsCommand(Solv solver, double newSideLength) {
        // Виконуємо команду зміни параметрів
    }

    private static void undoLastCommand(Solv solver) {
        // Скасовуємо останню команду
    }
}
