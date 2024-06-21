package Tasks;

import java.util.Objects;

public class Task {
    protected int id;
    protected String taskname;
    protected Status status;
    protected String description;

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

    public Task(String taskname, String description) {
        this.taskname = taskname;
        this.status = Status.NEW;
        this.description = description;
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
}



