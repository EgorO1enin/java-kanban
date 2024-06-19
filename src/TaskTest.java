import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashMap;
//InMemoryTaskManager taskManager = new InMemoryTaskManager();

class TaskTest {

    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
    InMemoryTaskManager taskManager = new InMemoryTaskManager();


    @Test
    public void shouldReturnEquals(){
        Task task1 = new Task("Task 1", "testing task");
        Task task2 = new Task("Task 2", "testing task");
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        int id = taskManager.taskId;
        Task firstCall = taskManager.getTask(id);
        Task secondCall = taskManager.getTask(id);

        assertEquals(firstCall, secondCall, "Они не равны");
    }

    @Test
    void addNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description");
        final int taskId = taskManager.addTask(task);

        final Task savedTask = taskManager.getTask(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final HashMap<Integer, Task> tasks = taskManager.getSimpleTaskList();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(1), "Задачи не совпадают.");
    }

    @Test
    void add() {

        Task task = new Task("Test addNewTask", "Test addNewTask description");
        historyManager.add(task);
        final ArrayList<Task> history = historyManager.getHistoryList();
        assertNotNull(history, "История не пустая.");
        assertEquals(1, history.size(), "История не пустая.");
    }

    @Test
    void ShouldBeEqualsBeforeAndAfterAddToManager(){
        Task task = new Task("Test addNewTask", "Test addNewTask description");
        //Первая задача будет под первым айди
        taskManager.addTask(task);
        assertEquals(task, taskManager.getTask(1));
    }
}
