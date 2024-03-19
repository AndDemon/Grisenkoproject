package com.rx.javajxpr;

public class MainTest {
    public static void main(String[] args) {
        testMainFunctionality();
    }

    private static void testMainFunctionality() {
        System.out.println("Тестування основного функціоналу:");
        Solv solver = new Solv();
        solver.calculateAreas();

        TableDisplay tableDisplay = new SimpleTableDisplay();
        System.out.println("\nПочаткові результати:");
        tableDisplay.displayTable(solver.getData(), new String[]{"Трикутник", "Прямокутник", "Сума площ"});


        executeChangeParamsCommand(solver, 15.0);


        undoLastCommand(solver);


        System.out.println("\nРезультати після зміни параметрів:");
        tableDisplay.displayTable(solver.getData(), new String[]{"Трикутник", "Прямокутник", "Сума площ"});
    }

    private static void executeChangeParamsCommand(Solv solver, double newSideLength) {
        CommandManager commandManager = CommandManager.getInstance();


        ChangeParamsCommand changeParamsCommand = new ChangeParamsCommand(solver, newSideLength);
        commandManager.executeCommand(changeParamsCommand);

        System.out.println("\nРезультати після скасування останньої команди:");
    }

    private static void undoLastCommand(Solv solver) {
        CommandManager commandManager = CommandManager.getInstance();


        commandManager.undoLastCommand();

        System.out.println("\nResults After Undoing Last Command:");
    }
}
