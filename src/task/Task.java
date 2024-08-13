package task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {
    protected int id;
    protected String taskname;
    protected Status status;
    protected String description;
    private Type type = Type.TASK;
    private Duration duration;
    private LocalDateTime startTime;

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

    public LocalDateTime getEndTime(){
        return startTime.plus(duration);
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
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
        return "Задача: " + taskname + ". " + "Id задачи: " + id + ". Статус задачи: " + status;

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



