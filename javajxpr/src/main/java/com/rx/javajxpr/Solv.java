package com.rx.javajxpr;
import java.util.Scanner;

public class Solv {
    public Calc data;
    public double sideLength;

    public Solv() {
        this.data = new Calc(0, 0);
    }

    public void calculateAreas() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the binary side length: ");
        String binarySideLength = scanner.nextLine();

        sideLength = Integer.parseInt(binarySideLength, 2);


        double triangleArea = (Math.sqrt(3) / 4) * Math.pow(sideLength, 2);
        double squareArea = Math.pow(sideLength, 2);
        double totalArea = triangleArea + squareArea;


        data.setA(triangleArea);
        data.setB(squareArea);
        data.setResult(totalArea);
    }

    public void solve() {

        double result = sideLength * 2;
        data.setResult(result);
    }

    public void changeParams(double newSideLength) {
        this.sideLength = newSideLength;
    }

    public Calc getData() {
        return data;
    }

    public void setData(Calc data) {
        this.data = data;
    }
}
