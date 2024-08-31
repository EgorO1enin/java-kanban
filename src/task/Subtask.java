package task;

import java.time.Duration;
import java.time.LocalDateTime;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String taskname, String description, int epicId, LocalDateTime startTime, Duration duration) {
        super(taskname, description, startTime, duration);
        this.epicId = epicId;
        super.setType(TaskType.SUBTASK);

    }

    public Subtask(String taskname, String description, int epicId) {
        super(taskname, description);
        this.epicId = epicId;
        super.setType(TaskType.SUBTASK);
    }

    @Override
    public String toString() {
        return "Подзадача: " + getTaskname() + ". " + "id " + getId() + ". Статус задачи: " + getStatus();
    }

    public int getEpicId() {
        return epicId;
    }

    public final void setEpicId(int id) {
        this.epicId = id;
    }
}
