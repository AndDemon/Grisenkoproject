# Практика ООП з Java

Завдання 2 - Класи та об'єкти
1. Розробити клас, що серіалізується, для зберігання параметрів і результатів обчислень. Використовуючи агрегування, розробити клас для знаходження рішення задачі. 
2. Розробити клас для демонстрації в діалоговому режимі збереження та відновлення стану об'єкта, використовуючи серіалізацію. Показати особливості використання transient полів. 
3. Розробити клас для тестування коректності результатів обчислень та серіалізації/десеріалізації. Використовувати докладні коментарі для автоматичної генерації документації засобами javadoc.
4. Виконати індивідуальне завдання згідно номеру в списку:
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
