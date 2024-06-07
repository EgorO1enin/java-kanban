import java.util.Objects;

public class Task {
    String taskname;
    int id;
    Status status;

    public Task(String taskname, int id, Status status) {
        this.taskname = taskname;
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Задача: " + taskname + ". " + "id " + id + ". Статус задачи: " + status;

    }
}



