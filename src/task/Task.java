package task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    protected int id;
    protected String taskname;
    protected Status status;
    protected String description;
    protected TaskType type = TaskType.TASK;
    protected Duration duration;
    protected LocalDateTime startTime;

    public Task(String taskname, String description, LocalDateTime startTime, Duration duration) {
        this.taskname = taskname;
        this.status = Status.NEW;
        this.description = description;
        this.startTime = startTime;
        this.duration = duration;
    }

    public Task(String taskname, String description) {
        this.taskname = taskname;
        this.status = Status.NEW;
        this.description = description;
    }

    public LocalDateTime getEndTime() {
        return startTime.plus(duration);
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(taskname, task.taskname) && status == task.status && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskname, status, description);
    }

    @Override
    public String toString() {
        return "Задача: " + taskname + ". " + "Id задачи: " + id + ". " +  "Описание задачи: " + description + ". " + "Тип задачи: " + type + ". " + "Длительность задачи: " + duration + ". " + "Начало задачи: " + startTime + ". " + "Статус задачи: " + status;

    }

    public int getId() {
        return id;
    }

    public String getTaskname() {
        return taskname;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}



