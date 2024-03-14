# Практика ООП з Java
Продемонструвати можливість паралельної обробки елементів колекції (пошук мінімуму, максимуму, обчислення середнього значення, відбір за критерієм, статистична обробка тощо).
Управління чергою завдань (команд) реалізувати за допомогою шаблону Worker Thread.
Додані класи:
```java
package org.example;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
public class ParallelProcessor {
    private final ExecutorService executorService;

    public ParallelProcessor(int parallelism) {
        this.executorService = Executors.newFixedThreadPool(parallelism);
    }

    public void processTasks(List<Runnable> tasks) {
        List<Callable<Void>> callableTasks = tasks.stream()
                .map(task -> (Callable<Void>) () -> {
                    task.run();
                    return null;
                })
                .collect(Collectors.toList());

        try {
            executorService.invokeAll(callableTasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    public void awaitTermination() {
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

```

```java
package org.example;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private final Thread workerThread;

    public TaskQueue() {
        workerThread = new Thread(() -> {
            try {
                while (true) {
                    Runnable task = queue.take();
                    task.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        workerThread.start();
    }

    public void submitTask(Runnable task) {
        queue.add(task);
    }

    public void shutdown() {
        workerThread.interrupt();
    }
}

```

```java
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

```

```java
ackage org.example;

public class MainTest {
    public static void main(String[] args) {
        testMainFunctionality();
    }

    private static void testMainFunctionality() {
        // Виведення повідомлення про тестування основного функціоналу
        System.out.println("Тестування основного функціоналу:");
        // Створення об'єкта solver класу Solv
        Solv solver = new Solv();
        // Виклик методу для розрахунку площ
        solver.calculateAreas();

        // Створення об'єкта tableDisplay класу SimpleTableDisplay
        TableDisplay tableDisplay = new SimpleTableDisplay();
        // Виведення початкових результатів
        System.out.println("\nПочаткові результати:");
        // Відображення даних у вигляді таблиці
        tableDisplay.displayTable(solver.getData(), new String[]{"Трикутник", "Прямокутник", "Сума площ"});

        // Виклик методу для зміни параметрів
        executeChangeParamsCommand(solver, 15.0);

        // Виклик методу для скасування останньої команди
        undoLastCommand(solver);

        // Виведення результатів після зміни параметрів
        System.out.println("\nРезультати після зміни параметрів:");
        // Відображення оновлених даних у вигляді таблиці
        tableDisplay.displayTable(solver.getData(), new String[]{"Трикутник", "Прямокутник", "Сума площ"});
    }

    private static void executeChangeParamsCommand(Solv solver, double newSideLength) {
        // Отримання екземпляра CommandManager
        CommandManager commandManager = CommandManager.getInstance();

        // Створення команди для зміни параметрів
        ChangeParamsCommand changeParamsCommand = new ChangeParamsCommand(solver, newSideLength);
        // Виконання команди
        commandManager.executeCommand(changeParamsCommand);

        // Виведення результатів після зміни параметрів
        System.out.println("\nРезультати після скасування останньої команди:");
    }

    private static void undoLastCommand(Solv solver) {
        // Отримання екземпляра CommandManager
        CommandManager commandManager = CommandManager.getInstance();

        // Скасування останньої команди
        commandManager.undoLastCommand();

        // Виведення результатів після скасування останньої команди
        System.out.println("\nResults After Undoing Last Command:");
    }
}
```


трошки підладнав код під завдання
