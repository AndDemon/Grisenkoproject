# Практика ООП з Java
Завдання 3 - Спадкування
Як основа використовувати вихідний текст проекту попередньої лабораторної роботи. Забезпечити розміщення результатів обчислень уколекції з можливістю збереження/відновлення.
Використовуючи шаблон проектування Factory Method (Virtual Constructor), розробити ієрархію, що передбачає розширення рахунок додавання нових відображуваних класів.
Розширити ієрархію інтерфейсом "фабрикованих" об'єктів, що представляє набір методів для відображення результатів обчислень.
Реалізувати ці методи виведення результатів у текстовому виде.
Розробити тареалізувати інтерфейс для "фабрикуючого" методу.


```java
package org.example;
import java.io.Serializable;

public class Calc implements Serializable, CalcDisplay {
    private static final long serialVersionUID = 1L;
    // Параметри
    private double parameter1;
    private double parameter2;
    // Результат
    private double result;
    // Конструктор
    public Calc(double parameter1, double parameter2) {
        this.parameter1 = parameter1;
        this.parameter2 = parameter2;
    }
    // Гетери і сетери
    public double getParameter1() {
        return parameter1;
    }

    public void setParameter1(double parameter1) {
        this.parameter1 = parameter1;
    }

    public double getParameter2() {
        return parameter2;
    }

    public void setParameter2(double parameter2) {
        this.parameter2 = parameter2;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    // Реалізація відображення параметрів обчислень
    @Override
    public void displayParameters(Calc data) {
        System.out.println("Параметр 1: " + data.getParameter1());
        System.out.println("Параметр 2: " + data.getParameter2());
    }

    // Реалізація відображення результатів обчислень
    @Override
    public void displayResult(Calc data) {
        System.out.println("Результат: " + data.getResult());
    }
}

```

```java
package org.example;

public class Solv{
    public Calc calculationData;
    public double parameter_1;
    public double parameter_2;
    public double result;

    public Solv(double parameter1, double parameter2) {
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
```

```java
package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Створення списку для зберігання об'єктів
        List<Calc> calculations = new ArrayList<>();
        // Створення об'єктів та передача їм параметрів
        Solv solver1 = new Solv(60, 3);
        Solv solver2 = new Solv(23, 34);

        solver1.solve();
        solver2.solve();

        calculations.add(solver1.getCalculationData());
        calculations.add(solver2.getCalculationData());
        // Серіалізація списку
        serializeObject(calculations, "data_collection.ser");

        // Десеріалізація об'єктів
        List<Calc> restoredCalculations = deserializeObject("data_collection.ser");
        // Виведення параметрів та результатів
        for (Calc restoredData : restoredCalculations) {
            restoredData.displayParameters(restoredData);
            restoredData.displayResult(restoredData);
            System.out.println("--------");
        }
    }
    // Метод для серіалізації списку
    private static void serializeObject(List<Calc> data, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Метод для десеріалізації
    private static List<Calc> deserializeObject(String fileName) {
        List<Calc> data = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            data = (List<Calc>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
```
![12](https://github.com/AndDemon/Grisenkoproject/assets/115999885/d82908ec-5cbe-4e55-bb00-2ad165232ad9)


```java
package org.example;

public interface CalcDisplay {
    //відображення параметрів обчислень
    void displayParameters(Calc data);
    //відображення результатів обчислень
    void displayResult(Calc data);
}
```

```java
package org.example;

public interface CalcFactory {
    // створення об'єкту з переданими параметрами
    Calc createCalc(double parameter1, double parameter2);
}

```

```java
package org.example;

public class CalcFactoryImpl implements CalcFactory {
    //створення об'єкту класу
    @Override
    public Calc createCalc(double parameter1, double parameter2) {
        return new Calc(parameter1, parameter2);
    }
}

```
