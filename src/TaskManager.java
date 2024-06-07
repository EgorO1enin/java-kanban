import java.util.HashMap;

public class TaskManager {
    int taskId = 1;
    HashMap<Integer, Task> simpleTask = new HashMap<>();
    HashMap<Integer, Epic>  epicTask = new HashMap<>();
    HashMap<Integer, Subtusk> subTusk = new HashMap<>();

    public void addTask(Task task){ // Добавление простой задачи
        task.id = taskId;
        taskId++;
        simpleTask.put(task.id, task);
    }

    public void addEpic(Epic epTask){ // Добавление эпика
        epTask.id = taskId;
        taskId++;
        epicTask.put(epTask.id, epTask);
    }

    public void addSubtusk (Subtusk subTask){
        subTask.id = taskId;
        taskId++;
        subTusk.put(subTask.id, subTask);
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
        }

    }

    void changeStatusOfEpiceTask(int id, Status epicStatus){
        epicTask.get(id).status = epicStatus;
    }

    void changeStatusOfSimpleTask(int id, Status simpleStatus){
       simpleTask.get(id).status = simpleStatus;
    }

    void changeStatusOfSubTask(int id, Status subStatus){
        subTusk.get(id).status = subStatus;
        int epicTaskId = subTusk.get(id).colId;
        int countDone = 0;
        int countInProgress = 0;
        int size = subTusk.size();
        for (int i : epicTask.get(epicTaskId).itemIds){
            if (subTusk.get(i).status == Status.DONE){
                countDone++;
            } else if (subTusk.get(i).status == Status.IN_PROGRESS){
                countInProgress++;
            }
        }
        if (size == countDone){
            epicTask.get(epicTaskId).status = Status.DONE;
        } else if (countInProgress >= 1 || (countDone >= 1 && countDone < size)){
            epicTask.get(epicTaskId).status = Status.IN_PROGRESS;
        } else {
            epicTask.get(epicTaskId).status = Status.NEW;
        }
    }
}
