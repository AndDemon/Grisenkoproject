package org.example;

public class SimpleTableDisplay implements TableDisplay {
    @Override
    public void displayTable(Calc data, String[] headers) {
        System.out.println("Simple Table Display:");
        for (String header : headers) {
            System.out.print(header + "\t");
        }
        System.out.println("\n---------------------------------");
        System.out.println(data.getA() + "\t" + data.getB() + "\t" + data.getOutput());
        System.out.println("---------------------------------");
    }
}