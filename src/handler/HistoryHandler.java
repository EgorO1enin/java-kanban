package handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import adapters.DurationAdapter;
import adapters.LocalDateTimeAdapter;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;

public class HistoryHandler extends TaskHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response;
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(Duration.class, new DurationAdapter())
                .create();
        try {
            if (httpExchange.getRequestMethod().equals("GET")) {
                response = gson.toJson(taskManager.getAllHistory());
                byte[] responesBytes = response.getBytes(StandardCharsets.UTF_8);
                httpExchange.sendResponseHeaders(200, responesBytes.length);
                OutputStream os = httpExchange.getResponseBody();
                os.write(response.getBytes());
            } else {
                httpExchange.sendResponseHeaders(404, 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
