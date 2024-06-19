import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {

    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
    InMemoryTaskManager taskManager = new InMemoryTaskManager();


    @Test
    public void shouldReturnEquals(){
        Epic epic = new Epic("Task 1", "testing task");
        Subtask subtask = new Subtask("Subtask 1", "Test", epic.getId());
        taskManager.addEpic(epic);
        taskManager.addSubtusk(subtask);
        int subId = subtask.getId();
        int taskId = taskManager.taskId;
        Task firstCall = taskManager.getEpic(taskId);
        Task secondCall = taskManager.getEpic(taskId);

        assertEquals(firstCall, secondCall, "Они не равны");
        assertEquals(taskManager.getSubtask(subId), taskManager.getSubtask(subId), "Они не равны");
    }



}