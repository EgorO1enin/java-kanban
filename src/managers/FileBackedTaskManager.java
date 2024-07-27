package managers;

import task.*;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;

public class FileBackedTaskManager extends InMemoryTaskManager implements TaskManager {

    String fileName;

    public FileBackedTaskManager(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int addTask(Task task) {
        save();
        return super.addTask(task);

    }

    @Override
    public int addEpic(Epic epTask) {
        return super.addEpic(epTask);
    }

    @Override
    public int addSubtusk(Subtask subTask) {
        return super.addSubtusk(subTask);
    }

    @Override
    public void deleteAllTasks() {
        super.deleteAllTasks();
    }

    @Override
    public void deleteTaskByInd(int index) {
        super.deleteTaskByInd(index);
    }

    @Override
    public void changeStatusOfEpiceTask(int id, Status epicStatus) {
        super.changeStatusOfEpiceTask(id, epicStatus);
    }

    @Override
    public Task getTaskById(int id) {
        return super.getTaskById(id);
    }

    @Override
    public Task getEpicById(int id) {
        return super.getEpicById(id);
    }

    @Override
    public Task getSubtaskById(int id) {
        return super.getSubtaskById(id);
    }

    @Override
    public HashMap<Integer, Task> getSimpleTaskList() {
        return super.getSimpleTaskList();
    }

    @Override
    public HashMap<Integer, Epic> getEpicTaskList() {
        return super.getEpicTaskList();
    }

    @Override
    public HashMap<Integer, Subtask> getSubTaskList() {
        return super.getSubTaskList();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
    }

    @Override
    public List<Task> getAllHistory() {
        return super.getAllHistory();
    }

    @Override
    public void removeTaskFromHistory(int id) {
        super.removeTaskFromHistory(id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public String toString(Task task) {
        return task.getId() + "," + task.getType() + "," +  task.getTaskname() + ","
                + task.getStatus().toString() + "," + task.getDescription();
    }

    public void save(){
        StringBuilder sb = new StringBuilder();
        sb.append("id,type,name,status,description,epic\n");
        for (Task task : getAllHistory()) {
            sb.append(toString(task)).append("\n");

        }

        for (Epic epic : getEpicTaskList().values()) {
            sb.append(toString(epic)).append("\n");
            for (Subtask subtask : getSubTaskList().values()) {
                sb.append(toString(subtask)).append("\n");
            }
        }

        for (Task task : getSimpleTaskList().values()) {
            sb.append(toString(task)).append("\n");
        }

        try(FileWriter fileWriter = new FileWriter(fileName)) {
          fileWriter.write(sb.toString());
        } catch (Exception e){
            System.out.print("Ошибка");
        }
    }


}
