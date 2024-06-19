import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    InMemoryTaskManager taskManager = new InMemoryTaskManager();


    Task task1 = new Task("Task 1", "testing task");
    Epic epic1 = new Epic("Эпик 1", "Нужно сделать");
    Subtask subtask1 = new Subtask("Subtask1 создания ",
            "Написать что то ", taskManager.addEpic(epic1));

    @Test
    public void shouldNotBeNullWhenTMAddTask(){
        taskManager.addTask(task1);
        assertNotNull(taskManager.getSimpleTaskList(), "Не добавляет тип данных Task");

    }

    @Test
    public void shouldNotBeNullWhenTMAddEpic(){
        taskManager.addEpic(epic1);
        assertNotNull(taskManager.getEpicTaskList(), "Не добавляет тип данных Task");

    }

    @Test
    public void shouldNotBeNullWhenTMAddSubtask(){
        taskManager.addSubtusk(subtask1);
        assertNotNull(taskManager.getSubTaskList(), "Не добавляет тип данных Task");

    }

    @Test
    public void shouldBeEqualsBeforeAndAfterAddTask(){
        taskManager.addTask(task1);
        assertEquals(task1, taskManager.getSimpleTaskList().get(2));
    }

    @Test
    public void shouldNotBeEqualsWhenWeSearchByIndex(){
        taskManager.addTask(task1);
        Task ret = taskManager.getTask(2);
        assertEquals(task1, ret);
    }

    @Test
    public void genId(){
        Task task1 = new Task("Task 1", "testing task");
        taskManager.addTask(task1);
    }

}