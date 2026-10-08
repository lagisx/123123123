package com.boxing.controller;

import com.boxing.model.AppModel;
import com.boxing.model.TrainingLog;
import com.boxing.model.Workout;
import com.boxing.util.TableUtil;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class WorkoutsController {
    @FXML private TableView<Workout> table;
    @FXML private TableColumn<Workout, String> titleCol, levelCol, roundsCol, minutesCol, descCol;
    @FXML private TextField nameField, descField;
    @FXML private ComboBox<String> levelBox;
    @FXML private Spinner<Integer> roundsSpinner, minutesSpinner;

    private final AppModel model = AppModel.get();

    @FXML
    private void initialize() {
        table.setItems(model.workouts);
        TableUtil.bind(titleCol, w -> w.title);
        TableUtil.bind(levelCol, w -> w.level);
        TableUtil.bind(roundsCol, w -> w.rounds);
        TableUtil.bind(minutesCol, w -> w.duration_min);
        TableUtil.bind(descCol, w -> w.description);
        roundsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 3));
        minutesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 180, 30));
        levelBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void onAdd() {
        if (nameField.getText().isBlank()) return;
        Workout w = new Workout();
        w.title = nameField.getText();
        w.level = levelBox.getValue();
        w.rounds = roundsSpinner.getValue();
        w.duration_min = minutesSpinner.getValue();
        w.description = descField.getText();
        model.addWorkout(w);
        nameField.clear();
        descField.clear();
    }

    @FXML
    private void onDelete() {
        Workout w = table.getSelectionModel().getSelectedItem();
        if (w != null) model.deleteWorkout(w);
    }

    @FXML
    private void onLog() {
        Workout w = table.getSelectionModel().getSelectedItem();
        if (w == null) { model.status.set("Выберите тренировку в таблице"); return; }
        TrainingLog t = new TrainingLog();
        t.workout_title = w.title;
        t.rounds_done = w.rounds;
        t.notes = "";
        model.addLog(t);
    }
}
