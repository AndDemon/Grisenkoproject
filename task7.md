# Практика ООП з Java
Розробити ієрархію класів відповідно до шаблону Observer (java) та продемонструвати можливість обслуговування розробленої раніше колекції (об'єкт, що спостерігається, Observable) різними (не менше двох) спостерігачами (Observers) – відстеження змін, упорядкування, висновок, відображення і т.д.
При реалізації ієрархії класів використати інструкції (Annotation). Відзначити особливості різних політик утримання анотацій (annotation retention policies). Продемонструвати підтримку класів концепції рефлексії (Reflection).
Використовуючи раніше створені класи, розробити додаток, що відображає результати обробки колекції об'єктів у графічному вигляді
Забезпечити діалоговий інтерфейс з користувачем та перемальовування графіка під час зміни значень елементів колекції.

```java
package com.rx.javajxpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        // Отримуємо контролер після завантаження FXML
        HelloController controller = fxmlLoader.getController();
        controller.setStage(stage); // Передаємо stage контролеру

        Scene scene = new Scene(root);
        stage.setTitle("Калькулятор суми площ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

```

![Screenshot 2024-03-20 000736](https://github.com/AndDemon/Grisenkoproject/assets/115999885/ce4f6839-0703-4448-ac1b-0636aeb56751)


```java
package com.rx.javajxpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private TextField sideLengthTextField;

    @FXML
    private Label resultLabel;

    private Observable observable = new Observable();
    private Stage stage;

    // Setter для stage
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    protected void changeParamsButtonClicked(ActionEvent event) {
        String sideLengthText = sideLengthTextField.getText();

        // Перевірка, чи введено лише "0" або "1"
        if (!sideLengthText.matches("[01]+")) {
            showAlert("Помилка", "Невірний формат", "Довжина сторони може бути тільки 0 або 1 в двійковій системі числення.");
            return; // Перериваємо обробку подальших команд
        }

        try {
            int sideLength = Integer.parseInt(sideLengthText);
            double triangleArea = (Math.sqrt(3) / 4) * Math.pow(sideLength, 2);
            double squareArea = Math.pow(sideLength, 2);
            double totalArea = triangleArea + squareArea;

            resultLabel.setText("Площа трикутника: " + triangleArea + "\n Площа квадрата: " + squareArea + "\n Загальна площа: " + totalArea);
        } catch (NumberFormatException e) {
            showAlert("Помилка", "Невірний формат", "Невірний формат введених даних для довжини сторони.");
        }
    }

    // Метод для показу вікна сповіщень
    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void initialize() {
        // Додаємо спостережачів до спостережуваного об'єкта
        observable.addObserver(new DataTracker());
        observable.addObserver(new DataProcessor());
    }
}

```

```java
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.control.Button?>

<VBox alignment="CENTER" prefHeight="200.0" prefWidth="500.0" spacing="10.0" xmlns="http://javafx.com/javafx/15.0.1" xmlns:fx="http://javafx.com/fxml/1"
      fx:controller="com.rx.javajxpr.HelloController">
    <Label text="Введіть довжину сторони у двійковій системі числення: " />
    <TextField fx:id="sideLengthTextField" />
    <Button text="Обчислити суму площ" onAction="#changeParamsButtonClicked" />
    <Label fx:id="resultLabel" />
</VBox>
```

```java
package com.rx.javajxpr;
import java.util.ArrayList;
import java.util.List;

// Інтерфейс спостерігача (Observer)
interface Observer {
    void update(List<Integer> data);
}

// Клас спостережуваного об'єкта (Observable)
public class Observable {
    private List<Observer> observers = new ArrayList<>();
    private List<Integer> collection = new ArrayList<>();

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void addData(int data) {
        collection.add(data);
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(collection);
        }
    }
}

```
