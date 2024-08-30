package task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Integer> subTaskList = new ArrayList<>();
    private LocalDateTime endTime;

    public Epic(String taskname, String description) {
        super(taskname, description);
        super.setType(TaskType.EPIC);

    }

    public Epic(String taskname, String description, LocalDateTime startTime, Duration duration) {
        super(taskname, description, startTime, duration);
        super.setType(TaskType.EPIC);
    }

    public void setNewSubList() {
        this.subTaskList = new ArrayList<>();
    }

    @Override
    public TaskType getType() {
        return super.getType();
    }

    @Override
    public String toString() {
        return "Задача ЭПИК: " + getTaskname() + ". " + "Id задачи: " + getId() + ". Статус задачи: " + getStatus();
    }

    public ArrayList<Integer> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(int id) {
        subTaskList.add(id);
    }

    public void deleteSubtaskFromEpic(Subtask subtask) {
        subTaskList.remove(subtask);
    }

    @Override
    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setSubTaskList(ArrayList<Integer> subTaskList) {
        this.subTaskList = subTaskList;
    }
}
