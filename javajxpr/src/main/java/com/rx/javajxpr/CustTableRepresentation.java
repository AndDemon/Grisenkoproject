package com.rx.javajxpr;

public class CustTableRepresentation implements CalcDisplay {
    private String parameter1Label;
    private String parameter2Label;
    private String outputLabel;

    public CustTableRepresentation(String parameter1Label, String parameter2Label, String outputLabel) {
        this.parameter1Label = parameter1Label;
        this.parameter2Label = parameter2Label;
        this.outputLabel = outputLabel;
    }

    @Override
    public void displayParameters(Calc data) {
        System.out.println(parameter1Label + ": " + data.getA());
        System.out.println(parameter2Label + ": " + data.getB());
    }

    @Override
    public void displayResult(Calc data) {
        System.out.println(outputLabel + ": " + data.getOutput());
    }
}