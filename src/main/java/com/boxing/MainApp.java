package com.boxing;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/boxing/view/Main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/com/boxing/view/style.css").toExternalForm());
        stage.setTitle("Боксёрский тренер");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(args); }
}
