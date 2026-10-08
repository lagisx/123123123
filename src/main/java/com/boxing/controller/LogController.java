package com.boxing.controller;

import com.boxing.model.AppModel;
import com.boxing.model.TrainingLog;
import com.boxing.model.Workout;
import com.boxing.util.TableUtil;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LogController {
    @FXML private TableView<TrainingLog> table;
    @FXML private TableColumn<TrainingLog, String> dateCol, titleCol, roundsCol, notesCol;
    @FXML private ComboBox<String> pickBox;
    @FXML private Spinner<Integer> roundsSpinner;
    @FXML private TextField notesField;
    @FXML private Label statsLabel;

    private final AppModel model = AppModel.get();

    @FXML
    private void initialize() {
        table.setItems(model.logs);
        TableUtil.bind(dateCol, t -> t.trained_on);
        TableUtil.bind(titleCol, t -> t.workout_title);
        TableUtil.bind(roundsCol, t -> t.rounds_done);
        TableUtil.bind(notesCol, t -> t.notes);
        roundsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 3));

        model.workouts.addListener((ListChangeListener<Workout>) c -> fillTitles());
        model.logs.addListener((ListChangeListener<TrainingLog>) c -> updateStats());
        fillTitles();
        updateStats();
    }

    private void fillTitles() {
        pickBox.getItems().setAll(model.workouts.stream().map(w -> w.title).toList());
    }

    private void updateStats() {
        int total = model.logs.stream().mapToInt(t -> t.rounds_done == null ? 0 : t.rounds_done).sum();
        statsLabel.setText("Всего тренировок: " + model.logs.size() + "   •   Всего раундов: " + total);
    }

    @FXML
    private void onAdd() {
        if (pickBox.getValue() == null) return;
        TrainingLog t = new TrainingLog();
        t.workout_title = pickBox.getValue();
        t.rounds_done = roundsSpinner.getValue();
        t.notes = notesField.getText();
        model.addLog(t);
        notesField.clear();
    }

    @FXML
    private void onDelete() {
        TrainingLog t = table.getSelectionModel().getSelectedItem();
        if (t != null) model.deleteLog(t);
    }
}
