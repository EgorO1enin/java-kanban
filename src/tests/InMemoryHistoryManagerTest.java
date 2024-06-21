package tests;

import Managers.InMemoryHistoryManager;
import Managers.InMemoryTaskManager;
import Tasks.Status;
import Tasks.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
    InMemoryTaskManager taskManager = new InMemoryTaskManager();


    @Test
    public void shouldbeNot() {
        Task task = new Task("Tasks.Task 1", "testing task");
        taskManager.addTask(task);
        historyManager.add(task);
        taskManager.getTaskById(1).setStatus(Status.DONE);
        taskManager.updateTask(taskManager.getTaskById(1));
        assertEquals(task.getStatus(), historyManager.getHistoryList().getFirst().getStatus());
    }
}