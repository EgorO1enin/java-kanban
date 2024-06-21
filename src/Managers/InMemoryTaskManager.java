package Managers;

import Tasks.Epic;
import Tasks.Status;
import Tasks.Subtask;
import Tasks.Task;

import java.util.HashMap;
import java.util.ArrayList;
// Должен стать интерфейсом
public class InMemoryTaskManager implements ТaskManager {
    private final HistoryManager historyManager = new InMemoryHistoryManager();
    int taskId = 1;
    private final HashMap<Integer, Task> tasksList = new HashMap<>();
    private final HashMap<Integer, Epic>  epicsList = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasksList = new HashMap<>();

    @Override
    public int addTask(Task task){ // Добавление простой задачи
        task.setId(taskId);
        taskId++;
        tasksList.put(task.getId(), task);
        return task.getId();
    }

    @Override
    public int addEpic(Epic epTask){ // Добавление эпика
        epTask.setId(taskId);
        taskId++;
        epicsList.put(epTask.getId(), epTask);
        return epTask.getId();
    }

    @Override
    public int addSubtusk(Subtask subTask){
        subTask.setId(taskId);
        Epic epic = epicsList.get(subTask.getEpicId());
        epic.setSubTaskList(taskId);
        taskId++;
        subtasksList.put(subTask.getId(), subTask);
        return subTask.getId();
    }

    @Override
    public void deleteAllTasks(){
        tasksList.clear();
        epicsList.clear();
        subtasksList.clear();
        taskId = 1;
    }

    @Override
    public void deleteTaskByInd(int index){
        if (tasksList.containsKey(index)){
            tasksList.remove(index);
        } else if (epicsList.containsKey(index)) {
            epicsList.remove(index);
        } else if (subtasksList.containsKey(index)){
            subtasksList.remove(index);
        } else {
            System.out.println("Задача под таким индексом не найдена!");
        }

    }

    @Override
    public void changeStatusOfEpiceTask(int id, Status epicStatus){
        if (!epicsList.containsKey(id)){
            System.out.println();
        }
        epicsList.get(id).setStatus(epicStatus);
    }

    public Task getTaskById(int id){
        historyManager.add(tasksList.get(id));
        return tasksList.get(id);
    }

    public Task getEpicById(int id){
        historyManager.add(epicsList.get(id));
        return epicsList.get(id);
    }

    public Task getSubtaskById(int id){
        historyManager.add(subtasksList.get(id));
        return subtasksList.get(id);
    }

    @Override
    public HashMap<Integer, Task> getSimpleTaskList() {
        return tasksList;
    }

    @Override
    public HashMap<Integer, Epic> getEpicTaskList() {
        return epicsList;
    }

    @Override
    public HashMap<Integer, Subtask> getSubTaskList() {
        return subtasksList;
    }

    @Override
    public void updateTask(Task task){
        if (tasksList.containsKey(task.getId())) {
            tasksList.put(task.getId(), task);
        }
    }

    @Override
    public void updateEpic(Epic epic){
        if (epicsList.containsKey(epic.getId())) {
            epicsList.put(epic.getId(), epic);
        }
    }

    @Override
    public void updateSubtask(Subtask subtask){
        subtasksList.put(subtask.getId(), subtask);
        Epic epic = epicsList.get(subtask.getEpicId());
        updateStatusEpic(epic);

    }

    public ArrayList<Task> getHistoryList(){
        return historyManager.getHistoryList();
    }

    public String getHistory(){
        return historyManager.getHistory();
    }

    private void updateStatusEpic(Epic epic) {
        ArrayList<Subtask> subtasks = new ArrayList<>();
        int countNew = 0;
        int countDone = 0;
        for (int i = 0; i < epic.getSubTaskList().size(); i++) {
            subtasks.add(subtasksList.get(epic.getSubTaskList().get(i)));
        }

        for (Subtask subTusk : subtasks) {
            if (subTusk.getStatus() == Status.DONE) {
                countDone++;
            } else if (subTusk.getStatus() == Status.NEW) {
                countNew++;
            }
        }

        if (subtasks.size() == countDone) {
            epic.setStatus(Status.DONE);
        } else if (countNew == subtasks.size()) {
            epic.setStatus(Status.NEW);
        } else {
            epic.setStatus(Status.IN_PROGRESS);
        }
    }
}

