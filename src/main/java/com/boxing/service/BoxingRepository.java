package com.boxing.service;

import com.boxing.model.TrainingLog;
import com.boxing.model.Workout;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/** Доступ к данным приложения (таблицы workouts и training_logs). */
public class BoxingRepository {
    private final SupabaseClient client = new SupabaseClient();

    public List<Workout> loadWorkouts() throws Exception {
        return client.select("workouts", "id.asc", new TypeToken<List<Workout>>() {}.getType());
    }

    public List<TrainingLog> loadLogs() throws Exception {
        return client.select("training_logs", "trained_on.desc", new TypeToken<List<TrainingLog>>() {}.getType());
    }

    public void add(String table, Object o) throws Exception { client.insert(table, o); }

    public void remove(String table, long id) throws Exception { client.delete(table, id); }
}
