package org.example;


public class MainTest {
    public static void main(String[] args) {
        testSimpleTableDisplay();
        testFancyTableDisplay();
    }

    private static void testSimpleTableDisplay() {
        System.out.println("Testing Simple:");
        ExtCalcFactory factory = new ExtCalcFactoryImpl();

        Solv solver = new Solv(10, 23);
        solver.solve();
        Calc data = solver.getData();

        TableDisplay tableDisplay = new SimpleTableDisplay();
        tableDisplay.displayTable(data, new String[]{"Param1", "Param2", "Result"});
    }

    private static void testFancyTableDisplay() {
        System.out.println("Testing Fancy:");
        ExtCalcFactory factory = new ExtCalcFactoryImpl();

        Solv solver = new Solv(20, 4);
        solver.solve();
        Calc data = solver.getData();

        TableDisplay tableDisplay = new FancyTableDisplay();
        tableDisplay.displayTable(data, new String[]{"Param1", "Param2", "Result"});
    }
}
