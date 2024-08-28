package handler;

import com.google.gson.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import managers.InMemoryTaskManager;
import adapters.DurationAdapter;
import adapters.LocalDateTimeAdapter;
import task.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class TaskHandler implements HttpHandler {
    public static InMemoryTaskManager taskManager = new InMemoryTaskManager();
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
                    if (Pattern.matches("^/tasks$", httpExchange.getRequestURI().getPath())) {
                        if (taskManager.getSimpleTaskList().isEmpty()) {
                            response = "Список задач пуст! Пожалуйста добавьте задачу!";
                        } else {
                            response = gson.toJson(taskManager.getSimpleTaskList());
                        }
                        byte[] responesBytes = response.getBytes(StandardCharsets.UTF_8);
                        httpExchange.sendResponseHeaders(200, responesBytes.length);
                        try (OutputStream os = httpExchange.getResponseBody()) {
                            os.write(response.getBytes());
                        }
                        httpExchange.close();
                    } else if (Pattern.matches("^/tasks/\\d+$", httpExchange.getRequestURI().getPath())) {
                        String taskId = httpExchange.getRequestURI().getPath().replaceFirst("/tasks/", "");
                        boolean found = false;
                        for (int id : taskManager.getSimpleTaskList().keySet()) {
                            if (id == Integer.parseInt(taskId)) {
                                found = true;
                            }
                        }
                        if (!found) {
                            System.out.println("Task not found! Или получен некорретный id!");
                            httpExchange.sendResponseHeaders(404, 0);
                        } else {
                            response = gson.toJson(taskManager.getTaskById(Integer.parseInt(taskId)));
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
                    /*taskManager.addTask(new Task("rgserg", "wqrfqrfqwrf",
                            LocalDateTime.of(LocalDate.now(), LocalTime.parse("11:02")), Duration.ofMinutes(1)));
                    taskManager.addTask(new Task("rgservqwrgerg", "rgqq3rgqwrg",
                            LocalDateTime.of(LocalDate.now(), LocalTime.parse("13:02")), Duration.ofMinutes(1)));
                    httpExchange.sendResponseHeaders(200, 0);
                    httpExchange.close();*/
                    try {
                        String requestBody;
                        try (InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(), StandardCharsets.UTF_8)){
                            StringBuilder stringBuilder = new StringBuilder();
                            char[] buffer = new char[1024];
                            int read;
                            while ((read = isr.read(buffer)) != -1) {
                                stringBuilder.append(buffer, 0, read);
                            }
                            requestBody = stringBuilder.toString();
                        }
                        Task task = gson.fromJson(requestBody, Task.class);
                        if (!requestBody.contains("id")) {
                            if (taskManager.addTask(task) == 0) {
                                httpExchange.sendResponseHeaders(406, 0);
                                System.out.println(task + " пересекается");
                                httpExchange.close();
                            } else {
                                taskManager.addTask(task);
                                httpExchange.sendResponseHeaders(200, 0);
                                httpExchange.close();
                            }
                        } else {
                            taskManager.updateTask(task);
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
                    /*Gson gson = new GsonBuilder()
                            .registerTypeAdapter(Duration.class, new DurationAdapter())
                            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                            .registerTypeAdapter(Task.class, new TaskAdapter())
                            .setPrettyPrinting()
                            .create();
                    String stringRequestBody;
                    //Для логирования запроса
                    try (InputStream is = httpExchange.getRequestBody();
                         InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                         BufferedReader br = new BufferedReader(isr)) {
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line);
                        }
                        stringRequestBody = sb.toString();
                    }
                    System.out.println("Received JSON: " + stringRequestBody);
                    Task task = gson.fromJson(stringRequestBody, Task.class);
                    taskManager.addTask(task);
                    httpExchange.sendResponseHeaders(200, 0);
                    System.out.println(task + " added");
                    httpExchange.close();*/
                    break;
                }
                    case "DELETE": {
                        String taskId = httpExchange.getRequestURI().getPath()
                                .replaceFirst("/tasks/", "");
                        boolean found = false;
                        for (int id : taskManager.getSimpleTaskList().keySet()) {
                            if (id == Integer.parseInt(taskId)) {
                                found = true;
                            }
                        }
                        if (!found) {
                            response = "Task not found!";
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
                default:{
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
