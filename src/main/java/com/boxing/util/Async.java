package com.boxing.util;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import javafx.application.Platform;

/** Выполнение запросов в фоне, результат возвращается в поток JavaFX. */
public final class Async {
    private Async() {}

    public static <T> void run(Callable<T> job, Consumer<T> onOk, Consumer<String> onError) {
        Thread t = new Thread(() -> {
            try {
                T result = job.call();
                Platform.runLater(() -> { onError.accept(""); onOk.accept(result); });
            } catch (Exception ex) {
                Platform.runLater(() -> onError.accept("⚠ " + ex.getMessage()));
            }
        });
        t.setDaemon(true);
        t.start();
    }
}
