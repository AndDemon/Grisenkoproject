package com.rx.javajxpr;

public class CalcFactoryImpl implements CalcFactory {
    //створення об'єкту класу
    @Override
    public Calc createCalc(double parameter1, double parameter2) {
        return new Calc(parameter1, parameter2);
    }
}
