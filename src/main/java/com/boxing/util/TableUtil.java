package com.boxing.util;

import java.util.function.Function;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;

public final class TableUtil {
    private TableUtil() {}

    public static <T> void bind(TableColumn<T, String> col, Function<T, Object> getter) {
        col.setCellValueFactory(d -> {
            Object v = getter.apply(d.getValue());
            return new SimpleStringProperty(v == null ? "" : v.toString());
        });
    }
}
