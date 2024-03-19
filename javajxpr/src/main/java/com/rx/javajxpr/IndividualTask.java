package com.rx.javajxpr;
import java.util.Scanner;

public class IndividualTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть довжину сторони (двійкове число): ");
        String binarySideLength = scanner.nextLine();


        int sideLength = Integer.parseInt(binarySideLength, 2);

        double triangleArea = (Math.sqrt(3) / 4) * Math.pow(sideLength, 2);

        double squareArea = Math.pow(sideLength, 2);

        double totalArea = triangleArea + squareArea;

        System.out.println("Сума площ: " + totalArea);
    }
}