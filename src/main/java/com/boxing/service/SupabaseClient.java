package com.boxing.service;

import com.boxing.config.SupabaseConfig;
import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/** Низкоуровневый клиент Supabase REST (PostgREST). */
public class SupabaseClient {
    private final HttpClient http = HttpClient.newHttpClient();
    private final Gson gson = new Gson();
    private final String key = SupabaseConfig.ANON_KEY;

    private HttpRequest.Builder req(String path) {
        return HttpRequest.newBuilder(URI.create(SupabaseConfig.URL + "/rest/v1/" + path))
                .header("apikey", key)
                .header("Authorization", "Bearer " + key)
                .header("Content-Type", "application/json");
    }

    private String send(HttpRequest r) throws Exception {
        HttpResponse<String> res = http.send(r, HttpResponse.BodyHandlers.ofString());
        if (res.statusCode() >= 300) throw new RuntimeException("Ошибка Supabase " + res.statusCode() + ": " + res.body());
        return res.body();
    }

    public <T> List<T> select(String table, String order, Type listType) throws Exception {
        return gson.fromJson(send(req(table + "?select=*&order=" + order).GET().build()), listType);
    }

    public void insert(String table, Object obj) throws Exception {
        send(req(table).header("Prefer", "return=minimal")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(obj))).build());
    }

    public void delete(String table, long id) throws Exception {
        send(req(table + "?id=eq." + id).DELETE().build());
    }
}
