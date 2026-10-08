package com.boxing.controller;

import com.boxing.model.AppModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainController {
    @FXML private BorderPane root;
    @FXML private Label statusLabel;

    private final Map<String, Parent> views = new HashMap<>();

    @FXML
    private void initialize() {
        statusLabel.textProperty().bind(AppModel.get().status);
        show("Workouts");
        AppModel.get().refresh();
    }

    @FXML private void showWorkouts() { show("Workouts"); }
    @FXML private void showLog() { show("Log"); }
    @FXML private void showTimer() { show("Timer"); }

    private void show(String name) {
        root.setCenter(views.computeIfAbsent(name, n -> {
            try {
                return FXMLLoader.load(getClass().getResource("/com/boxing/view/" + n + ".fxml"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
