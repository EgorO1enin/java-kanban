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
        Subtask subtask = new Subtask("Tasks.Subtask 1", "Test", epic.getId());
        taskManager.addEpic(epic);
        taskManager.addSubtusk(subtask);
        int subId = subtask.getId();
        int taskId = epic.getId();
        Task firstCall = taskManager.getEpicById(taskId);
        Task secondCall = taskManager.getEpicById(taskId);

        assertEquals(firstCall, secondCall, "Они не равны");
        assertEquals(taskManager.getSubtaskById(subId), taskManager.getSubtaskById(subId), "Они не равны");
    }



}