package org.example;

public class ExtCalcFactoryImpl implements ExtCalcFactory {
    @Override
    public Calc createCalc(double parameter1, double parameter2) {
        return new Calc(parameter1, parameter2);
    }

    @Override
    public TableDisplay createTableDisplay() {
        // вибрати тип відображення таблиці
        return new SimpleTableDisplay();
    }
}