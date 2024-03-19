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
