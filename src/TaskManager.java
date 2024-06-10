import java.util.HashMap;

public class TaskManager {
    int taskId = 1;
    private final HashMap<Integer, Task> tasksList = new HashMap<>();
    private final HashMap<Integer, Epic>  epicsList = new HashMap<>();
    private final HashMap<Integer, Subtusk> subtasksList = new HashMap<>();

    public void addTask(Task task){ // Добавление простой задачи
        task.setId(taskId);
        taskId++;
        tasksList.put(task.getId(), task);
    }

    public void addEpic(Epic epTask){ // Добавление эпика
        epTask.setId(taskId);
        taskId++;
        epicsList.put(epTask.getId(), epTask);
    }

    public void addSubtusk (Subtusk subTask){
        subTask.setId(taskId);
        taskId++;
        subtasksList.put(subTask.getId(), subTask);
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

    public void changeStatusOfSimpleTask(int id, Status simpleStatus){
       tasksList.get(id).setStatus(simpleStatus);
    }

    public void changeStatusOfSubTask(int id, Status subStatus){
        subtasksList.get(id).setStatus(subStatus);
        int epicTaskId = subtasksList.get(id).getEpicId();
        int countDone = 0;
        int countInProgress = 0;
        int size = subtasksList.size();
        for (int i : epicsList.get(epicTaskId).getSubTaskList()){
            if (subtasksList.get(i).getStatus() == Status.DONE){
                countDone++;
            } else if (subtasksList.get(i).getStatus() == Status.IN_PROGRESS){
                countInProgress++;
            }
        }
        if (size == countDone){
            epicsList.get(epicTaskId).setStatus(Status.DONE);
        } else if (countInProgress >= 1 || (countDone >= 1 && countDone < size)){
            epicsList.get(epicTaskId).setStatus(Status.IN_PROGRESS);
        } else {
            epicsList.get(epicTaskId).setStatus(Status.NEW);
        }
    }

    public HashMap<Integer, Task> getSimpleTask() {
        return tasksList;
    }

    public HashMap<Integer, Epic> getEpicTask() {
        return epicsList;
    }

    public HashMap<Integer, Subtusk> getSubTusk() {
        return subtasksList;
    }
}
