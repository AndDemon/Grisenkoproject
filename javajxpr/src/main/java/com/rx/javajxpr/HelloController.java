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
