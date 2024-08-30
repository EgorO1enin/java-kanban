package handler;

import com.google.gson.*;
import com.sun.net.httpserver.HttpExchange;
import adapters.DurationAdapter;
import adapters.LocalDateTimeAdapter;
import task.Epic;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class EpicHandler extends TaskHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String response;
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(Duration.class, new DurationAdapter())
                .create();
        try {
            switch (httpExchange.getRequestMethod()) {
                case "GET": {
                    if (Pattern.matches("^/epics$", httpExchange.getRequestURI().getPath())) {
                        if (taskManager.getEpicTaskList().isEmpty()) {
                            response = "Список Epic пуст! Пожалуйста добавьте Epic!";
                        } else {
                            response = gson.toJson(taskManager.getEpicTaskList());
                        }
                        byte[] responesBytes = response.getBytes(StandardCharsets.UTF_8);
                        httpExchange.sendResponseHeaders(200, responesBytes.length);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(response.getBytes());
                        }
                        httpExchange.close();
                    } else if (Pattern.matches("^/Epics/\\d+$", httpExchange.getRequestURI().getPath())) {
                        String taskId = httpExchange.getRequestURI().getPath().replaceFirst("/Epics/", "");
                        boolean found = false;
                        for (int id : taskManager.getEpicTaskList().keySet()) {
                            if (id == Integer.parseInt(taskId)) {
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("Epic not found! Или получен некорретный id!");
                            httpExchange.sendResponseHeaders(404, 0);
                        } else {
                            response = gson.toJson(taskManager.getEpicById(Integer.parseInt(taskId)));
                            byte[] responesBytes = response.getBytes(StandardCharsets.UTF_8);
                            httpExchange.sendResponseHeaders(200, responesBytes.length);
                            try (OutputStream os = httpExchange.getResponseBody()) {
                                os.write(response.getBytes());
                            }
                        }
                        httpExchange.close();
                    }
                    break;
                }
                case "POST": {
                    try {
                        String requestBody;
                        try (InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8)) {
                            StringBuilder stringBuilder = new StringBuilder();
                            char[] buffer = new char[1024];
                            int read;
                            while ((read = isr.read(buffer)) != -1) {
                                stringBuilder.append(buffer, 0, read);
                            }
                            requestBody = stringBuilder.toString();
                        }
                        Epic epic = gson.fromJson(requestBody, Epic.class);
                        epic.setNewSubList();
                        taskManager.addEpic(epic);
                        httpExchange.sendResponseHeaders(200, 0);
                        httpExchange.close();
                    } catch (Exception e) {
                        httpExchange.sendResponseHeaders(400, 0);
                        System.out.println("Что то пошло не так, проверьте правильность запроса!");
                        System.out.println(e.getMessage());
                        e.printStackTrace();
                        httpExchange.close();
                    }
                    break;
                }
                case "DELETE": {
                    String taskId = httpExchange.getRequestURI().getPath()
                            .replaceFirst("/epics/", "");
                    boolean found = false;
                    for (int id : taskManager.getEpicTaskList().keySet()) {
                        if (id == Integer.parseInt(taskId)) {
                            found = true;
                        }
                    }
                    if (!found) {
                        response = "Epic not found!";
                        httpExchange.sendResponseHeaders(404, 0);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(response.getBytes());
                        }
                        httpExchange.close();
                    } else {
                        taskManager.deleteTaskByInd(Integer.parseInt(taskId));
                        httpExchange.sendResponseHeaders(200, 0);
                        httpExchange.close();
                    }
                    break;

                }
                default: {
                    System.out.println("Ждем пост гет или делит запрос а получили -" + httpExchange.getRequestMethod());
                    httpExchange.sendResponseHeaders(405, 0);
                    httpExchange.close();
                }

            }
        } catch (Exception e) {
            System.out.println("Что то пошло не так, проверьте правильность запроса!");
            e.printStackTrace();
            httpExchange.close();
        }
    }
}
