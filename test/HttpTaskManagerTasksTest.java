import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.InMemoryTaskManager;
import managers.TaskManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import adapters.DurationAdapter;
import server.HttpTaskServer;
import adapters.LocalDateTimeAdapter;
import task.Task;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class HttpTaskManagerTasksTest {

    private final HttpTaskServer httpTaskServer = new HttpTaskServer();
    private final TaskManager taskManager = new InMemoryTaskManager();

    Gson gson = new GsonBuilder()
            .registerTypeAdapter(Duration.class, new DurationAdapter())
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();

    public HttpTaskManagerTasksTest() throws IOException {
    }


    @BeforeEach
    public void setUp() {
        httpTaskServer.startServer();
    }

    @AfterEach
    public void shutDown() {
        httpTaskServer.stopServer();
        taskManager.deleteAllTasks();
    }

    @Test
    public void testAddTask() throws IOException, InterruptedException {
        // создаём задачу
        // конвертируем её в JSON
        Task task = new Task("taskName", "ddesc", LocalDateTime.now(), Duration.ofMinutes(15));
        String json = gson.toJson(task);
        // создаём HTTP-клиент и запрос
        HttpClient client = HttpClient.newHttpClient();
        URI url = URI.create("http://localhost:8085/tasks");
        HttpRequest request = HttpRequest.newBuilder().uri(url).POST(HttpRequest.BodyPublishers.ofString(json)).build();

        // вызываем рест, отвечающий за создание задач
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        // проверяем код ответа
        assertEquals(200, response.statusCode());

        // проверяем, что создалась одна задача с корректным именем
        assertNotNull(taskManager.getSimpleTaskList(), "Задачи не возвращаются");
    }

    @Test
    void getTasks() throws IOException, InterruptedException {
        Task task = new Task("Test 2", "Testing task 2", LocalDateTime.now(), Duration.ofMinutes(5));
        taskManager.addTask(task);
        HttpClient client = HttpClient.newHttpClient();
        URI url = URI.create("http://localhost:8085/tasks");
        HttpRequest request = HttpRequest.newBuilder().uri(url).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertNotNull(taskManager.getSimpleTaskList(), "Задачи не возвращаются");
        assertEquals(1, taskManager.getSimpleTaskList().size(), "Некорректное количество задач");


    }
}