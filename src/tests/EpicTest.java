package tests;

import task.Epic;
import managers.InMemoryHistoryManager;
import managers.InMemoryTaskManager;
import task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {

    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
    InMemoryTaskManager taskManager = new InMemoryTaskManager();


    @Test
    public void shouldReturnEquals(){
        Epic epic = new Epic("Tasks.Task 1", "testing task");
        taskManager.addEpic(epic);
        int taskId = epic.getId();
        Task epicById = taskManager.getEpicById(taskId);

        assertEquals(taskManager.getEpicById(taskId), epicById, "Они не равны");
    }



}