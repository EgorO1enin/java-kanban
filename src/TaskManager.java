import java.util.HashMap;
import java.util.ArrayList;

public class TaskManager {
    int taskId = 1;
    private final HashMap<Integer, Task> tasksList = new HashMap<>();
    private final HashMap<Integer, Epic>  epicsList = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasksList = new HashMap<>();

    public int addTask(Task task){ // Добавление простой задачи
        task.setId(taskId);
        taskId++;
        tasksList.put(task.getId(), task);
        return task.id;
    }

    public int addEpic(Epic epTask){ // Добавление эпика
        epTask.setId(taskId);
        taskId++;
        epicsList.put(epTask.getId(), epTask);
        return epTask.id;
    }

    public int addSubtusk (Subtask subTask){
        subTask.setId(taskId);
        Epic epic = epicsList.get(subTask.getEpicId());
        epic.setSubTaskList(taskId);
        taskId++;
        subtasksList.put(subTask.getId(), subTask);
        return subTask.id;
    }

    public void deleteAllTasks(){
        tasksList.clear();
        epicsList.clear();
        subtasksList.clear();
        taskId = 1;
    }

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

    public void changeStatusOfEpiceTask(int id, Status epicStatus){
        if (!epicsList.containsKey(id)){
            System.out.println();
        }
        epicsList.get(id).setStatus(epicStatus);
    }

    public HashMap<Integer, Task> getSimpleTask() {
        return tasksList;
    }

    public HashMap<Integer, Epic> getEpicTask() {
        return epicsList;
    }

    public HashMap<Integer, Subtask> getSubTask() {
        return subtasksList;
    }

    public void updateTask(Task task){
        if (tasksList.containsKey(task.getId())) {
            tasksList.put(task.getId(), task);
        }
    }

    public void updateEpic(Epic epic){
        if (epicsList.containsKey(epic.getId())) {
            epicsList.put(epic.getId(), epic);
        }
    }

    private void updateStatusEpic(Epic epic){
        ArrayList <Subtask> subtasks = new ArrayList<>();
        int countNew = 0;
        int countDone = 0;
        for (int i = 0; i < epic.getSubTaskList().size(); i++){
            subtasks.add(subtasksList.get(epic.getSubTaskList().get(i)));
        }

        for (Subtask subTusk : subtasks){
            if (subTusk.getStatus() == Status.DONE){
                countDone++;
            }else if (subTusk.getStatus() == Status.NEW){
                countNew++;
            }
        }

        if (subtasks.size() == countDone){
            epic.setStatus(Status.DONE);
        } else if (countNew == subtasks.size()){
            epic.setStatus(Status.NEW);
        } else{
            epic.setStatus(Status.IN_PROGRESS);
        }

    }

    public void updateSubtask(Subtask subtask){
        subtasksList.put(subtask.getId(), subtask);
        Epic epic = epicsList.get(subtask.getEpicId());
        updateStatusEpic(epic);

    }
}

