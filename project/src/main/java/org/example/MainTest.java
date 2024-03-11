package org.example;

public class MainTest {
    public static void main(String[] args) {
        testMainFunctionality();
    }

    private static void testMainFunctionality() {
        System.out.println("Testing Main Functionality:");
        Solv solver = new Solv();
        solver.calculateAreas();

        TableDisplay tableDisplay = new SimpleTableDisplay();
        System.out.println("\nInitial Results:");
        tableDisplay.displayTable(solver.getData(), new String[]{"Triangle Area", "Square Area", "Total Area"});


        executeChangeParamsCommand(solver, 15.0);


        undoLastCommand(solver);


        System.out.println("\nUpdated Results:");
        tableDisplay.displayTable(solver.getData(), new String[]{"Triangle Area", "Square Area", "Total Area"});
    }

    private static void executeChangeParamsCommand(Solv solver, double newSideLength) {
        CommandManager commandManager = CommandManager.getInstance();


        ChangeParamsCommand changeParamsCommand = new ChangeParamsCommand(solver, newSideLength);
        commandManager.executeCommand(changeParamsCommand);

        System.out.println("\nResults After Changing Parameters:");
    }

    private static void undoLastCommand(Solv solver) {
        CommandManager commandManager = CommandManager.getInstance();


        commandManager.undoLastCommand();

        System.out.println("\nResults After Undoing Last Command:");
    }
}
