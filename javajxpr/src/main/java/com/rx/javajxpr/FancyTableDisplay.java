package com.rx.javajxpr;

public class FancyTableDisplay implements TableDisplay {
    @Override
    public void displayTable(Calc data, String[] headers) {

        for (String header : headers) {
            System.out.print(String.format("%-30s", header));
        }
        System.out.println("\n----------------------------------------------------");


        double output = data.getOutput();
        if (!Double.isNaN(output)) {
            System.out.print(String.format("%-30s", data.getA()));
            System.out.print(String.format("%-30s", data.getB()));
            System.out.println(String.format("%-30s", output));
        } else {
            System.out.println("No valid result available.");
        }
        System.out.println("----------------------------------------------------");
    }
}
