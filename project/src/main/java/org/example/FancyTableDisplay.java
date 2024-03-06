package org.example;

public class FancyTableDisplay implements TableDisplay {
    @Override
    public void displayTable(Calc data, String[] headers) {
        System.out.println("Fancy Table Display:");
        for (String header : headers) {
            System.out.print(header + "\t\t\t\t");
        }
        System.out.println("\n----------------------------------------------------");
        System.out.println(data.getA() + "\t\t\t\t" + data.getB() + "\t\t\t\t" + data.getOutput());
        System.out.println("----------------------------------------------------");
    }
}