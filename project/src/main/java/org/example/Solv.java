package org.example;

public class Solv {
    public Calc data;
    public double x;
    public double y;
    public double z;

    public Solv(double a, double b) {
        x = a;
        y = b;
        data = new Calc(a, b);
    }
    public void solve() {
        z = x - y;
        data.setResult(z);
    }
    public Calc getData() {
        return data;
    }
    public void setData(Calc data) {
        this.data = data;
    }
}