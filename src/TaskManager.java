import java.util.HashMap;

public class TaskManager {
    int taskId = 1;
    private final HashMap<Integer, Task> simpleTask = new HashMap<>();
    private final HashMap<Integer, Epic>  epicTask = new HashMap<>();
    private final HashMap<Integer, Subtusk> subTusk = new HashMap<>();

    public void addTask(Task task){ // Добавление простой задачи
        task.setId(taskId);
        taskId++;
        simpleTask.put(task.getId(), task);
    }

    public void addEpic(Epic epTask){ // Добавление эпика
        epTask.setId(taskId);
        taskId++;
        epicTask.put(epTask.getId(), epTask);
    }

    public void addSubtusk (Subtusk subTask){
        subTask.setId(taskId);
        taskId++;
        subTusk.put(subTask.getId(), subTask);
    }

    public void deleteAllTasks(){
        simpleTask.clear();
        epicTask.clear();
        subTusk.clear();
        taskId = 1;
    }

    public void deleteTaskByInd(int index){
        if (simpleTask.containsKey(index)){
            simpleTask.remove(index);
        } else if (epicTask.containsKey(index)) {
            epicTask.remove(index);
        } else if (subTusk.containsKey(index)){
            subTusk.remove(index);
        } else {
            System.out.println("Задача под таким индексом не найдена!");
        }

    }

    public void changeStatusOfEpiceTask(int id, Status epicStatus){
        if (!epicTask.containsKey(id)){
            System.out.println();
        }
        epicTask.get(id).setStatus(epicStatus);
    }

    public void changeStatusOfSimpleTask(int id, Status simpleStatus){
       simpleTask.get(id).setStatus(simpleStatus);
    }

    public void changeStatusOfSubTask(int id, Status subStatus){
        subTusk.get(id).setStatus(subStatus);
        int epicTaskId = subTusk.get(id).getEpicId();
        int countDone = 0;
        int countInProgress = 0;
        int size = subTusk.size();
        for (int i : epicTask.get(epicTaskId).getItemIds()){
            if (subTusk.get(i).getStatus() == Status.DONE){
                countDone++;
            } else if (subTusk.get(i).getStatus() == Status.IN_PROGRESS){
                countInProgress++;
            }
        }
        if (size == countDone){
            epicTask.get(epicTaskId).setStatus(Status.DONE);
        } else if (countInProgress >= 1 || (countDone >= 1 && countDone < size)){
            epicTask.get(epicTaskId).setStatus(Status.IN_PROGRESS);
        } else {
            epicTask.get(epicTaskId).setStatus(Status.NEW);
        }
    }

    public HashMap<Integer, Task> getSimpleTask() {
        return simpleTask;
    }

    public HashMap<Integer, Epic> getEpicTask() {
        return epicTask;
    }

    public HashMap<Integer, Subtusk> getSubTusk() {
        return subTusk;
    }
}
