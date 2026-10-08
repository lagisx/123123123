package com.boxing.model;

import com.boxing.service.BoxingRepository;
import com.boxing.util.Async;
import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** Состояние приложения (единый источник данных для всех контроллеров). */
public class AppModel {
    private static final AppModel INSTANCE = new AppModel();
    public static AppModel get() { return INSTANCE; }

    private final BoxingRepository repo = new BoxingRepository();




    public final ObservableList<Workout> workouts = FXCollections.observableArrayList();
    public final ObservableList<TrainingLog> logs = FXCollections.observableArrayList();
    public final StringProperty status = new SimpleStringProperty("");

    private AppModel() {}




    public void refresh() {
        status.set("");
        Async.run(repo::loadWorkouts, workouts::setAll, status::set);
        Async.run(repo::loadLogs, logs::setAll, status::set);
    }









    public void addWorkout(Workout w) {
        Async.run(() -> { repo.add("workouts", w); return null; }, r -> refresh(), status::set);
    }

    public void deleteWorkout(Workout w) {
        Async.run(() -> { repo.remove("workouts", w.id); return null; }, r -> refresh(), status::set);
    }

    public void addLog(TrainingLog t) {
        if (t.trained_on == null) t.trained_on = LocalDate.now().toString();
        Async.run(() -> { repo.add("training_logs", t); return null; }, r -> { refresh(); status.set("Записано в дневник!"); }, status::set);
    }



    public void deleteLog(TrainingLog t) {
        Async.run(() -> { repo.remove("training_logs", t.id); return null; }, r -> refresh(), status::set);
    }




}
