package Tasks;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTaskList = new ArrayList<>();

    public Epic(String taskname, String description) {
        super(taskname, description);
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

}
