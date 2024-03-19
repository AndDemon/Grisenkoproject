package com.rx.javajxpr;

public interface CalcFactory {
    // створення об'єкту з переданими параметрами
    Calc createCalc(double parameter1, double parameter2);
}
