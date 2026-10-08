import com.boxing.model.TrainingLog;
import com.boxing.model.Workout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModelJsonTest {
    private final Gson gson = new Gson();

    @Test
    public void workoutToJson() {
        Workout w = new Workout(); w.title = "Бой с тенью"; w.duration_min = 30;
        String json = gson.toJson(w);
        assertTrue(json.contains("\"duration_min\":30"));
    }
    @Test
    public void workoutFromJson() {
        Workout w = gson.fromJson(
                "{\"id\":1,\"title\":\"Скакалка\",\"level\":\"Новичок\",\"rounds\":3}", Workout.class);
        assertEquals("Скакалка", w.title);
        assertEquals(Optional.of(3), w.rounds);
    }

}