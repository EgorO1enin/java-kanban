package managers;

import task.Epic;
import task.Status;
import task.Subtask;
import task.Task;

import java.time.Duration;
import java.util.*;

// Должен стать интерфейсом
public class InMemoryTaskManager implements TaskManager {
    private final HistoryManager historyManager = new InMemoryHistoryManager();
    int taskId = 1;
    protected final HashMap<Integer, Task> tasksList = new HashMap<>();
    protected final HashMap<Integer, Epic>  epicsList = new HashMap<>();
    protected final HashMap<Integer, Subtask> subtasksList = new HashMap<>();
    CompareTasks compareTasks = new CompareTasks();
    protected TreeSet<Task> taskTreeSet = new TreeSet<>(compareTasks);

    @Override
    public int addTask(Task task) {
        boolean hasOverlap = taskTreeSet.stream().anyMatch(existingTask -> areTasksOverlapping(existingTask, task));
        if (!hasOverlap) {
            addTaskToSortedTreeSet(task);
            task.setId(taskId);
            taskId++;
            tasksList.put(task.getId(), task);
            return task.getId();
        } else {
            System.out.println("Происходит наложение Task");
            return 0;
        }


    }

    @Override
    public int addEpic(Epic epTask) {
        boolean hasOverlap = taskTreeSet.stream().anyMatch(existingTask -> areTasksOverlapping(existingTask, epTask));
        if (!hasOverlap) {// Добавление эпика
            addTaskToSortedTreeSet(epTask);
            epTask.setId(taskId);
            taskId++;
            epicsList.put(epTask.getId(), epTask);
            return epTask.getId();
        }else {
            System.out.println("Происходит наложение Epic");
            return 0;
        }
    }

    @Override
    public int addSubtusk(Subtask subTask) {
        addTaskToSortedTreeSet(subTask);
        subTask.setId(taskId);
        Epic epic = epicsList.get(subTask.getEpicId());
        epic.setSubTaskList(taskId);
        taskId++;
        subtasksList.put(subTask.getId(), subTask);
        return subTask.getId();
    }

    @Override
    public void deleteAllTasks() {
        tasksList.clear();
        epicsList.clear();
        subtasksList.clear();
        taskId = 1;
    }

    @Override
    public void deleteTaskByInd(int index) {
        if (tasksList.containsKey(index)) {
            tasksList.remove(index);
        } else if (epicsList.containsKey(index)) {
            epicsList.remove(index);
        } else if (subtasksList.containsKey(index)) {
            subtasksList.remove(index);
        } else {
            System.out.println("Задача под таким индексом не найдена!");
        }

    }

    @Override
    public void changeStatusOfEpiceTask(int id, Status epicStatus) {
        if (!epicsList.containsKey(id)) {
            System.out.println();
        }
        epicsList.get(id).setStatus(epicStatus);
    }

    @Override
    public Task getTaskById(int id) {
        historyManager.add(tasksList.get(id));
        return tasksList.get(id);
    }

    @Override
    public Task getEpicById(int id) {
        historyManager.add(epicsList.get(id));
        return epicsList.get(id);
    }

    @Override
    public Task getSubtaskById(int id) {
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
    public void updateTask(Task task) {
        if (tasksList.containsKey(task.getId())) {
            tasksList.put(task.getId(), task);
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epicsList.containsKey(epic.getId())) {
            epicsList.put(epic.getId(), epic);
        }
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasksList.put(subtask.getId(), subtask);
        Epic epic = epicsList.get(subtask.getEpicId());
        updateStatusEpic(epic);

    }

    @Override
    public List<Task> getAllHistory() {
        return historyManager.getHistory();
    }

    @Override
    public void removeTaskFromHistory(int id) {
        if (epicsList.containsKey(id)) {
            for (int ind : epicsList.get(id).getSubTaskList()) {
                historyManager.remove(ind);
            }
            historyManager.remove(id);
        } else {
            historyManager.remove(id);
        }
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

    public void printSortedTaskList() {
        System.out.println(taskTreeSet.toString());
    }

    public boolean areTasksOverlapping(Task task1, Task task2) {
        return task1.getStartTime().isBefore(task2.getEndTime()) && task2.getStartTime().isBefore(task1.getEndTime());
    }

    public Set<Task> getPrioritizedTasks() {
        return taskTreeSet;
    }

    public void addTaskToSortedTreeSet(Task task) {
        if (task.getStartTime() == null) {
            return;
        }
        taskTreeSet.add(task);
    }

    public void initEpicDuration(Epic epic, Subtask firstSub, Subtask lastSub){
       epic.setDuration(Duration.between(firstSub.getStartTime(), lastSub.getEndTime()));
    }











}

