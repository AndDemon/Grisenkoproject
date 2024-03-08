package org.example;
import java.io.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ExtCalcFactory factory = new ExtCalcFactoryImpl();
        // Створення списку для зберігання об'єктів
        List<Calc> calculations = new LinkedList<>();
        // Створення об'єктів та передача їм параметрів
        Solv solver1 = new Solv(60, 3);
        Solv solver2 = new Solv(23, 34);

        solver1.solve();
        solver2.solve();


        CommandManager commandManager = CommandManager.getInstance();

        // Використання макрокоманди для виконання та скасування команд
        MacroCommand macroCommand = new MacroCommand();
        macroCommand.addCommand(new SolveCommand(solver1));
        macroCommand.addCommand(new SolveCommand(solver2));

        commandManager.executeCommand(macroCommand);
        calculations.add(solver1.getData());
        calculations.add(solver2.getData());

        TableDisplay tableDisplay = chooseTableDisplay();

        List<Runnable> parallelTasks = new ArrayList<>();
        for (Calc data : calculations) {
            Runnable task = () -> tableDisplay.displayTable(data, new String[]{"Param1", "Param2", "Result"});
            parallelTasks.add(task);
        }

        // Using ParallelProcessor for parallel execution
        ParallelProcessor parallelProcessor = new ParallelProcessor(2);
        parallelProcessor.processTasks(parallelTasks);
        parallelProcessor.awaitTermination();

        // Using TaskQueue for task queue management
        TaskQueue taskQueue = new TaskQueue();
        for (Runnable task : parallelTasks) {
            taskQueue.submitTask(task);
        }

        // Undo the last command
        commandManager.undoLastCommand();

        // Shutdown the TaskQueue
        taskQueue.shutdown();
    }
    private static TableDisplay chooseTableDisplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Виберіть тип таблиці:");
        System.out.println("1. Simple Table Display");
        System.out.println("2. Fancy Table Display");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return new SimpleTableDisplay();
            case 2:
                return new FancyTableDisplay();
            default:
                System.out.println("Невірний вибір. Використано Simple Table Display за замовчуванням.");
                return new SimpleTableDisplay();
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