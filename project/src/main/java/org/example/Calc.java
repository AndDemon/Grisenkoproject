package org.example;
import java.io.Serializable;

public class Calc implements Serializable, CalcDisplay {
    private static final long serialVersionUID = 1L;
    // Параметри
    private double a;
    private double b;
    // Результат
    private double output;
    // Конструктор
    public Calc(double a, double b) {
        this.a = a;
        this.b = b;
    }
    // Гетери і сетери
    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getOutput() {
        return output;
    }

    public void setResult(double output) {
        this.output = output;
    }

    // Реалізація відображення параметрів обчислень
    @Override
    public void displayParameters(Calc data) {
        System.out.println("Parameter 1: " + data.getA());
        System.out.println("Parameter 2: " + data.getB());
    }

    // Реалізація відображення результатів обчислень
    @Override
    public void displayResult(Calc data) {
        System.out.println("Output: " + data.getOutput());
    }
}