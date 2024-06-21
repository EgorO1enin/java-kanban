package tests;

import Tasks.Epic;
import Managers.InMemoryHistoryManager;
import Managers.InMemoryTaskManager;
import Tasks.Subtask;
import Tasks.Task;
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