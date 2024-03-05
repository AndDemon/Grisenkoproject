# Практика ООП з Java

Завдання 2 - Класи та об'єкти
Розробити клас, що серіалізується, для зберігання параметрів і результатів обчислень. Використовуючи агрегування, розробити клас для знаходження рішення задачі.

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
        // Створення Solv
        Solv solver = new Solv(60, 23);

        // Виклик методу solve
        solver.solve();

        // Серіалізація
        saveObject(solver.getData(), "data.ser");

        // Десеріалізація
        Calc restoredData = loadObject("data.ser");

        // Вивід результатів
        System.out.println("Parameter 1: " + restoredData.getA());
        System.out.println("Parameter 2: " + restoredData.getB());
        System.out.println("Output: " + restoredData.getOutput());
    }

    private static void saveObject(Calc data, String fileName) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Calc loadObject(String fileName) {
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
