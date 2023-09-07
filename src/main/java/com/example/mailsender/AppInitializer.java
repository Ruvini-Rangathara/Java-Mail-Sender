package com.example.mailsender;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(FXMLLoader
                .load(getClass().getResource("/view/Form.fxml"))));
        stage.setTitle("Language Translator");
        stage.show();
    }
}
