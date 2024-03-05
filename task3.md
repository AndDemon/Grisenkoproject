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
```


```java
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
```


```java
package org.example;
import java.io.*;
import java.util.List;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        // Створення списку для зберігання об'єктів
        List<Calc> calculations = new LinkedList<>();
        // Створення об'єктів та передача їм параметрів
        Solv solver1 = new Solv(60, 3);
        Solv solver2 = new Solv(23, 34);

        solver1.solve();
        solver2.solve();

        calculations.add(solver1.getData());
        calculations.add(solver2.getData());
        // Серіалізація списку
        saveObject(calculations, "data_collection.ser");

        // Десеріалізація об'єктів
        List<Calc> restoredCalculations = loadObject("data_collection.ser");
        // Виведення параметрів та результатів
        for (Calc restoredData : restoredCalculations) {
            restoredData.displayParameters(restoredData);
            restoredData.displayResult(restoredData);
            System.out.println("--------");
        }
    }
    // Метод для серіалізації списку
    private static void saveObject(List<Calc> data, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Метод для десеріалізації
    private static List<Calc> loadObject(String fileName) {
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
