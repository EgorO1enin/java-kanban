public class Task {
    protected int id;
    private String taskname;
    private Status status;
    private String description;

    public Task(String taskname, String description) {
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

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}



