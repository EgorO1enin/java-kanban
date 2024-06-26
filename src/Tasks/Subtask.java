package Tasks;

public class Subtask extends Task {
    private final int epicId;

    public Subtask(String taskname, String description, int epicId) {
        super(taskname, description);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Подзадача: " + getTaskname() + ". " + "id " + getId() + ". Статус задачи: " + getStatus();
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int id) {
        this.id = id;
    }


}
