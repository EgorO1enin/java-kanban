package managers;

import task.Epic;
import task.Status;
import task.Subtask;
import task.Task;

import java.util.HashMap;

public interface ТaskManager {
    int addTask(Task task);

    int addEpic(Epic epTask);

    int addSubtusk(Subtask subTask);

    void deleteAllTasks();

    void deleteTaskByInd(int index);

    void changeStatusOfEpiceTask(int id, Status epicStatus);

    HashMap<Integer, Task> getSimpleTaskList();

    HashMap<Integer, Epic> getEpicTaskList();

    HashMap<Integer, Subtask> getSubTaskList();

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);
}
