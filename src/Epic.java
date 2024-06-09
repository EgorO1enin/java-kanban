import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> itemIds = new ArrayList<>();

    public Epic(int id, String taskname, String description) {
        super(id, taskname, description);
    }


    @Override
    public String toString() {
        return "Задача ЭПИК: " + getTaskname() + ". " + "Id задачи: " + getId() + ". Статус задачи: " + getStatus();

    }

    public ArrayList<Integer> getItemIds() {
        return itemIds;
    }

    public void setItemIds(ArrayList<Integer> itemIds) {
        this.itemIds = itemIds;
    }
}
