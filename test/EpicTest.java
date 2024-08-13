import task.Epic;
import managers.InMemoryHistoryManager;
import managers.InMemoryTaskManager;
import task.Status;
import task.Subtask;
import task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {

    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
    InMemoryTaskManager taskManager = new InMemoryTaskManager();


    @Test
    public void shouldReturnEquals() {
        Epic epic = new Epic("Tasks.Task 1", "testing task");
        taskManager.addEpic(epic);
        int taskId = epic.getId();
        Task epicById = taskManager.getEpicById(taskId);

        assertEquals(taskManager.getEpicById(taskId), epicById, "Они не равны");
    }

    @Test
    public void EpicStatusShouldBeNew(){
        Epic epic = new Epic("Epic Test", "testing epic");
        taskManager.addEpic(epic);
        Subtask subtask1 = new Subtask("Subtask 1", "testing subtask", 1);
        Subtask subtask2 = new Subtask("Subtask 2", "testing subtask", 1);
        taskManager.addSubtusk(subtask1);
        taskManager.addSubtusk(subtask2);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);
        assertEquals(epic.getStatus(), Status.NEW);
    }

    @Test
    public void EpicStatusShouldBeInProgress(){
        Epic epic = new Epic("Epic Test", "testing epic");
        taskManager.addEpic(epic);
        Subtask subtask1 = new Subtask("Subtask 1", "testing subtask", 1);
        Subtask subtask2 = new Subtask("Subtask 2", "testing subtask", 1);
        subtask1.setStatus(Status.DONE);
        taskManager.addSubtusk(subtask1);
        taskManager.addSubtusk(subtask2);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);
        assertEquals(epic.getStatus(), Status.IN_PROGRESS);
    }

    @Test
    public void EpicStatusShouldBeDone(){
        Epic epic = new Epic("Epic Test", "testing epic");
        taskManager.addEpic(epic);
        Subtask subtask1 = new Subtask("Subtask 1", "testing subtask", 1);
        Subtask subtask2 = new Subtask("Subtask 2", "testing subtask", 1);
        subtask1.setStatus(Status.DONE);
        subtask2.setStatus(Status.DONE);
        taskManager.addSubtusk(subtask1);
        taskManager.addSubtusk(subtask2);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);
        assertEquals(epic.getStatus(), Status.DONE);
    }

    @Test
    public void EpicStatusShouldInProgress(){
        Epic epic = new Epic("Epic Test", "testing epic");
        taskManager.addEpic(epic);
        Subtask subtask1 = new Subtask("Subtask 1", "testing subtask", 1);
        Subtask subtask2 = new Subtask("Subtask 2", "testing subtask", 1);
        subtask1.setStatus(Status.IN_PROGRESS);
        subtask2.setStatus(Status.IN_PROGRESS);
        taskManager.addSubtusk(subtask1);
        taskManager.addSubtusk(subtask2);
        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);
        assertEquals(epic.getStatus(), Status.IN_PROGRESS);
    }



}