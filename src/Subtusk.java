public class Subtusk extends Task {
    int colId;


    public Subtusk(String taskname, int id, Status status, int colId) {
        super(taskname, id, status);
        this.colId = colId;
    }


    @Override
    public String toString() {
        return "Подзадача: " + taskname + ". " + "id " + id + ". Статус задачи: " + status;
    }
}
