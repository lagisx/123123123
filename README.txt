Боксёрский тренер (Java 17 + JavaFX FXML + Supabase), архитектура MVC

Структура:
  model/       - Model: Workout, TrainingLog, AppModel (состояние приложения)
  service/     - доступ к Supabase (SupabaseClient, BoxingRepository)
  controller/  - Controller: Main/Workouts/Log/Timer Controller
  resources/com/boxing/view/ - View: *.fxml + style.css (интерфейс без логики)
  config/SupabaseConfig.java - URL и ключи

Запуск:
1. Выполните database/schema.sql в Supabase (SQL Editor).
2. Впишите URL и ключи в config/SupabaseConfig.java
3. mvn javafx:run
