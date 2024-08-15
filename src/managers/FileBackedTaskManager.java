package managers;

import task.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private final File fileName;

    public FileBackedTaskManager(File fileName) {
        this.fileName = fileName;
    }

    @Override
    public int addTask(Task task) {
        super.addTask(task);
        //addTaskToSortedTreeSet(task);
        save();
        return task.getId();
    }

    @Override
    public int addEpic(Epic epTask) {
        super.addEpic(epTask);
        //addTaskToSortedTreeSet(epTask);
        save();
        return epTask.getId();
    }

    @Override
    public int addSubtusk(Subtask subTask) {
        super.addSubtusk(subTask);
        save();
        return subTask.getId();
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

    private static String getEpicIdInSubtask(Task task) {
        if (task.getType().equals(TaskType.SUBTASK)) {
            return Integer.toString(((Subtask) task).getEpicId());
        }
        return "";
    }

    public String toString(Task task) {
        return task.getId() + ","
                + task.getType() + "," + task.getTaskname() + ","
                + task.getStatus().toString() + "," + task.getDescription() + ","
                + task.getStartTime() + "," + task.getEndTime() + "," + task.getDuration().toMinutes() + "," + getEpicIdInSubtask(task);
    }

    public static Task fromString(String value) {
        String[] parts = value.split(",");
        String id = parts[0];
        String type = parts[1];
        String name = parts[2];
        String status = parts[3];
        String description = parts[4];
        LocalDateTime startTime = LocalDateTime.parse(parts[5]);
        LocalDateTime endTime = LocalDateTime.parse(parts[6]);
        Duration duration = Duration.ofMinutes((Long.parseLong(parts[7])));
        Integer idOfEpic = type.equals(TaskType.SUBTASK.toString()) ? Integer.valueOf(parts[8]) : null;


        switch (type) {
            case "TASK":
                Task task = new Task(name, description);
                task.setId(Integer.parseInt(id));
                task.setStatus(Status.valueOf(status.toUpperCase()));
                task.setStartTime(startTime);
                task.setDuration(duration);
                return task;
            case "EPIC":
                Epic epic = new Epic(name, description);
                epic.setId(Integer.parseInt(id));
                epic.setStatus(Status.valueOf(status.toUpperCase()));
                epic.setStartTime(startTime);
                epic.setDuration(duration);
                return epic;
            case "SUBTASK":
                Subtask subtask = new Subtask(name, description, idOfEpic);
                subtask.setId(Integer.parseInt(id));
                subtask.setStatus(Status.valueOf(status.toUpperCase()));
                subtask.setStartTime(startTime);
                subtask.setDuration(duration);
                return subtask;
            default:
                return null;
        }
    }

    public static FileBackedTaskManager load(File fileName) throws FileNotFoundException {
        FileBackedTaskManager manager = new FileBackedTaskManager(fileName);
        try (BufferedReader br = new BufferedReader(new FileReader(fileName, StandardCharsets.UTF_8))) {
            String line = br.readLine();
            while (br.ready()) {
                line = br.readLine();
                Task task = fromString(line);
                if (task.getType().equals("EPIC")) {
                    manager.addEpic((Epic) fromString(line));
                } else if (task.getType().equals("TASK")) {
                    manager.addTask((Task) fromString(line));
                } else if (task.getType().equals("SUBTASK")) {
                    manager.addSubtusk((Subtask) fromString(line));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return manager;
    }

    private void save() {
        StringBuilder sb = new StringBuilder();
        sb.append("id,type,name,status,description,start time, end time, duration, epic\n");
        for (Task task : getSimpleTaskList().values()) {
            sb.append(toString(task)).append("\n");
        }
        for (Epic epic : getEpicTaskList().values()) {
            sb.append(toString(epic)).append("\n");
            for (Subtask subtask : getSubTaskList().values()) {
                sb.append(toString(subtask)).append("\n");
            }
        }
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(sb.toString());
        } catch (Exception e) {
            System.out.print("Ошибка");
        }
    }
}
