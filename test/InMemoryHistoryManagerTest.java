import managers.InMemoryHistoryManager;
import managers.InMemoryTaskManager;
import task.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;


import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

class InMemoryHistoryManagerTest {


    private InMemoryHistoryManager historyManager;
    private InMemoryTaskManager taskManager;

    @BeforeEach
    void setUp() {
        historyManager = new InMemoryHistoryManager();
        taskManager = new InMemoryTaskManager();
    }

    @Test
    void returnTrueWhenWeAddTask() {
        Task task1 = new Task("task1", "Task 1");
        Task task2 = new Task("task2", "Task 2");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.getTaskById(1);
        taskManager.getTaskById(2);
        List<Task> history = taskManager.getAllHistory();
        assertEquals(2, history.size());
        assertEquals(task1, history.get(0));
        assertEquals(task2, history.get(1));
    }

    @Test
    void returnTrueAfterRemoveTaskFromHistory() {
        Task task1 = new Task("task1", "Task 1");
        Task task2 = new Task("task2", "Task 2");
        Task task3 = new Task("task3", "Task 3");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.getTaskById(1);
        taskManager.getTaskById(2);
        taskManager.getTaskById(3);
        taskManager.removeTaskFromHistory(3);
        List<Task> history = taskManager.getAllHistory();
        assertEquals(2, history.size());
        assertEquals(task1, history.get(0));
        assertEquals(task2, history.get(1));
    }

    @Test
    void returnTrueWhenWetGetHistory() {
        Task task1 = new Task("task1", "Task 1");
        Task task2 = new Task("task2", "Tassprint k 2");
        Task task3 = new Task("task3", "Task 3");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.getTaskById(1);
        taskManager.getTaskById(2);
        taskManager.getTaskById(3);
        List<Task> history = taskManager.getAllHistory();
        assertEquals(3, history.size());
        assertEquals(task1, history.get(0));
        assertEquals(task2, history.get(1));
        assertEquals(task3, history.get(2));
    }

    @Test
    void lengthOfHistoryMustBeNull(){
        Task task1 = new Task("task1", "Task 1");
        taskManager.addTask(task1);
        taskManager.getTaskById(1);
        taskManager.getTaskById(1);
        List<Task> history = taskManager.getAllHistory();
        assertEquals(1, history.size());
    }

    @Test
    void lengthOfHistoryMustBe2AfterDeletingFirstTask(){
        Task task1 = new Task("task1", "Task 1");
        Task task2 = new Task("task2", "Tassprint k 2");
        Task task3 = new Task("task3", "Task 3");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.getTaskById(1);
        taskManager.getTaskById(2);
        taskManager.getTaskById(3);
        taskManager.removeTaskFromHistory(1);
        assertEquals(2, taskManager.getAllHistory().size());
    }

    @Test
    void lengthOfHistoryMustBe2AfterDeletingSecondTask(){
        Task task1 = new Task("task1", "Task 1");
        Task task2 = new Task("task2", "Tassprint k 2");
        Task task3 = new Task("task3", "Task 3");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.getTaskById(1);
        taskManager.getTaskById(2);
        taskManager.getTaskById(3);
        taskManager.removeTaskFromHistory(2);
        assertEquals(2, taskManager.getAllHistory().size());
    }

    @Test
    void lengthOfHistoryMustBe2AfterDeletingThirdTask(){
        Task task1 = new Task("task1", "Task 1");
        Task task2 = new Task("task2", "Tassprint k 2");
        Task task3 = new Task("task3", "Task 3");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.getTaskById(1);
        taskManager.getTaskById(2);
        taskManager.getTaskById(3);
        taskManager.removeTaskFromHistory(3);
        assertEquals(2, taskManager.getAllHistory().size());
    }


}