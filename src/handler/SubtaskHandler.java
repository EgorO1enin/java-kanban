package handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import adapters.DurationAdapter;
import adapters.LocalDateTimeAdapter;
import task.Epic;
import task.Subtask;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class SubtaskHandler extends TaskHandler {
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
                    if (Pattern.matches("^/subtasks$", httpExchange.getRequestURI().getPath())) {
                        if (taskManager.getSubTaskList().isEmpty()) {
                            response = "Список Subtask пуст! Пожалуйста добавьте задачу!";
                        } else {
                            response = gson.toJson(taskManager.getSubTaskList());
                        }
                        byte[] responesBytes = response.getBytes(StandardCharsets.UTF_8);
                        httpExchange.sendResponseHeaders(200, responesBytes.length);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(response.getBytes());
                        }
                        httpExchange.close();
                    } else if (Pattern.matches("^/subtasks/\\d+$", httpExchange.getRequestURI().getPath())) {
                        String taskId = httpExchange.getRequestURI().getPath().replaceFirst("/subtasks/", "");
                        boolean found = false;
                        for (int id : taskManager.getSubTaskList().keySet()) {
                            if (id == Integer.parseInt(taskId)) {
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("Subtask not found! Или получен некорретный id!");
                            httpExchange.sendResponseHeaders(404, 0);
                        } else {
                            response = gson.toJson(taskManager.getSubTaskList().get(Integer.parseInt(taskId)));
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

                        Subtask subtask = gson.fromJson(requestBody, Subtask.class);
                        Epic epic = (Epic) taskManager.getEpicById(subtask.getEpicId());
                        if (taskManager.addSubtusk(subtask) == 0) {
                            httpExchange.sendResponseHeaders(406, 0);
                            httpExchange.close();
                        } else {
                            taskManager.addSubtusk(subtask);
                            taskManager.addTaskToSortedTreeSet(subtask);
                            taskManager.initEpicDuration(epic, taskManager
                                    .getSubtaskByIdForEpicDuration(epic.getSubTaskList().getFirst()), taskManager
                                    .getSubtaskByIdForEpicDuration(epic.getSubTaskList().getLast()));
                            taskManager.updateEpic(epic);
                            httpExchange.sendResponseHeaders(200, 0);
                            httpExchange.close();
                        }

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
                            .replaceFirst("/subtasks/", "");
                    boolean found = false;
                    for (int id : taskManager.getSubTaskList().keySet()) {
                        if (id == Integer.parseInt(taskId)) {
                            found = true;
                        }
                    }
                    if (!found) {
                        response = "Subtask not found!";
                        httpExchange.sendResponseHeaders(404, 0);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(response.getBytes());
                        }
                        httpExchange.close();
                    } else {
                        Subtask subtask = (Subtask) taskManager.getSubtaskByIdForSubtask(Integer.parseInt(taskId));
                        Epic epic = (Epic) taskManager.getEpicById(subtask.getEpicId());
                        epic.deleteSubtaskFromEpic(subtask);
                        taskManager.updateEpic(epic);
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