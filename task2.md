# Практика ООП з Java

Завдання 2 - Класи та об'єкти
Розробити клас, що серіалізується, для зберігання параметрів і результатів обчислень. Використовуючи агрегування, розробити клас для знаходження рішення задачі.

```java
package org.example;
import java.io.Serializable;

public class Calc implements Serializable {
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
}
```
Розробити клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта, використовуючи серіалізацію. Показати особливості використання transient полів. 
Розробити клас для тестування коректності результатів обчислень та серіалізації/десеріалізації. Використовувати докладні коментарі для автоматичної генерації документації засобами javadoc.

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

public class Main {
    public static void main(String[] args) {
        // Створення Problem_Solver
        Solv solver = new Solv(60, 23);

        // Виклик методу solve
        solver.solve();

        // Серіалізація
        serializeObject(solver.getCalculationData(), "data.ser");

        // Десеріалізація
        Calc restoredData = deserializeObject("data.ser");

        // Вивід результатів
        System.out.println("Параметр 1: " + restoredData.getParameter1());
        System.out.println("Параметр 2: " + restoredData.getParameter2());
        System.out.println("Результат: " + restoredData.getResult());
    }

    private static void serializeObject(Calc data, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Calc deserializeObject(String fileName) {
        Calc data = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            data = (Calc) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
```
![1](https://github.com/AndDemon/Grisenkoproject/assets/115999885/f2ec7ab8-576f-4de1-b24a-a7720ec5e832)

Виконати індивідуальне завдання згідно номеру в списку:

```java
package org.example;
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
```
![4task](https://github.com/AndDemon/Grisenkoproject/assets/115999885/350bece5-e94f-4b03-b6de-1022392499ee)
