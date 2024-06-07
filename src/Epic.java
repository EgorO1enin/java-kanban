import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    ArrayList<Integer> itemIds = new ArrayList<>();

    public Epic(String taskname, int id, Status status, ArrayList<Integer> itemIds) {
        super(taskname, id, status);
        this.itemIds = itemIds;

    }


    @Override
    public String toString() {
        return "Задача ЭПИК: " + taskname + ". " + "Id задачи: " + id + ". Статус задачи: " + status;

    }
}
