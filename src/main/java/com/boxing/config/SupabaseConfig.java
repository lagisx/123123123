package com.boxing.config;

/** Ключи и адрес проекта Supabase. Заполните своими значениями (Project Settings -> API). */
public final class SupabaseConfig {
    private SupabaseConfig() {}

    public static final String URL = "https://xziaqtdfaeqttbeybnvu.supabase.co";
    /** Публичный (anon) ключ — используется приложением. */
    public static final String ANON_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh6aWFxdGRmYWVxdHRiZXlibnZ1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3OTE0NjAwMTgsImV4cCI6MjEwNzAzNjAxOH0.56XloFlWj5IG9RYVGQSygaXinwuJh35Huh8sLSTQAJQ";
    /** Сервисный ключ (service_role) — НИКОГДА не публикуйте его. Только для админ-операций. */
    public static final String SERVICE_KEY = "YOUR_SERVICE_ROLE_KEY";
}
