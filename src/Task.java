public class Task {
    private int id;
    private String taskname;
    private Status status;
    private String description;

    public Task(int id, String taskname, String description) {
        this.id = id;
        this.taskname = taskname;
        this.status = Status.NEW;
        this.description = description;
    }


    @Override
    public String toString() {
        return "Задача: " + taskname + ". " + "id " + id + ". Статус задачи: " + status;

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
}



