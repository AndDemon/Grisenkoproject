package org.example;

public interface CalcFactory {
    // створення об'єкту з переданими параметрами
    Calc createCalc(double parameter1, double parameter2);
}
