package org.example;

public class Problem_Solver{
    public Calc calculationData;
    public double parameter_1;
    public double parameter_2;
    public double result;

    public Problem_Solver(double parameter1, double parameter2) {
        this.parameter_1 = parameter1;
        this.parameter_2 = parameter2;
        this.calculationData = new Calc(parameter1, parameter2);
    }

    public void solve() {
        result = parameter_1 - parameter_2;
        calculationData.setResult(result);
    }

    public Calc getCalculationData() {
        return calculationData;
    }

    public void setCalculationData(Calc calculationData) {
        this.calculationData = calculationData;
    }
}