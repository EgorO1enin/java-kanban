import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
    InMemoryTaskManager taskManager = new InMemoryTaskManager();


    @Test
    public void shouldbeNot() {
        Task task = new Task("Task 1", "testing task");
        taskManager.addTask(task);
        historyManager.add(task);
        taskManager.getTask(1).setStatus(Status.DONE);
        taskManager.updateTask(taskManager.getTask(1));
        assertEquals(task.status, InMemoryHistoryManager.historyList.getFirst().status);
    }
}