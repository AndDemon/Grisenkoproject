# Практика ООП з Java
Завдання 4 - Поліморфізм

За основу використовувати вихідний текст проекту попередньої лабораторної роботи Використовуючи шаблон проектування Factory Method (Virtual Constructor), розширити ієрархію похідними класами, реалізують методи для подання результатів у вигляді текстової таблиці. Параметри відображення таблиці мають визначатися користувачем. Продемонструвати заміщення (перевизначення, overriding), поєднання (перевантаження, overloading), динамічне призначення методів (Пізнє зв'язування, поліморфізм, dynamic method dispatch). Забезпечити діалоговий інтерфейс із користувачем. Розробити клас для тестування основної функціональності. Використати коментарі для автоматичної генерації документації засобами javadoc.


```java
package org.example;
import java.io.*;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExtCalcFactory factory = new ExtCalcFactoryImpl();
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
        TableDisplay tableDisplay = chooseTableDisplay();

        for (Calc data : calculations) {
            tableDisplay.displayTable(data, new String[]{"Param1", "Param2", "Result"});
        }
    }
    private static TableDisplay chooseTableDisplay() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Виберіть тип таблиці:");
        System.out.println("1. Simple Table Display");
        System.out.println("2. Fancy Table Display");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                return new SimpleTableDisplay();
            case 2:
                return new FancyTableDisplay();
            default:
                System.out.println("Невірний вибір. Використано Simple Table Display за замовчуванням.");
                return new SimpleTableDisplay();
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
![234](https://github.com/AndDemon/Grisenkoproject/assets/115999885/5718d8ad-0c52-48c2-b96e-cacba9810707)


```java
package org.example;


public class MainTest {
    public static void main(String[] args) {
        testSimpleTableDisplay();
        testFancyTableDisplay();
    }

    private static void testSimpleTableDisplay() {
        System.out.println("Testing Simple:");
        ExtCalcFactory factory = new ExtCalcFactoryImpl();

        Solv solver = new Solv(10, 23);
        solver.solve();
        Calc data = solver.getData();

        TableDisplay tableDisplay = new SimpleTableDisplay();
        tableDisplay.displayTable(data, new String[]{"Param1", "Param2", "Result"});
    }

    private static void testFancyTableDisplay() {
        System.out.println("Testing Fancy:");
        ExtCalcFactory factory = new ExtCalcFactoryImpl();

        Solv solver = new Solv(20, 4);
        solver.solve();
        Calc data = solver.getData();

        TableDisplay tableDisplay = new FancyTableDisplay();
        tableDisplay.displayTable(data, new String[]{"Param1", "Param2", "Result"});
    }
}

```
![235](https://github.com/AndDemon/Grisenkoproject/assets/115999885/6a23fac5-f1c0-4382-bba0-62d21dbe01e2)


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

```java
package org.example;

public interface TableDisplay {
    void displayTable(Calc data, String[] headers);
}
```

```java
package org.example;

public class SimpleTableDisplay implements TableDisplay {
    @Override
    public void displayTable(Calc data, String[] headers) {
        System.out.println("Simple Table Display:");
        for (String header : headers) {
            System.out.print(header + "\t");
        }
        System.out.println("\n---------------------------------");
        System.out.println(data.getA() + "\t" + data.getB() + "\t" + data.getOutput());
        System.out.println("---------------------------------");
    }
}
```

```java
package org.example;

public class FancyTableDisplay implements TableDisplay {
    @Override
    public void displayTable(Calc data, String[] headers) {
        System.out.println("Fancy Table Display:");
        for (String header : headers) {
            System.out.print(header + "\t\t\t\t");
        }
        System.out.println("\n----------------------------------------------------");
        System.out.println(data.getA() + "\t\t\t\t" + data.getB() + "\t\t\t\t" + data.getOutput());
        System.out.println("----------------------------------------------------");
    }
}
```

```java
package org.example;

public interface ExtCalcFactory extends CalcFactory {
    TableDisplay createTableDisplay();
}
```

```java
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
```
