package Managers;

import Tasks.Task;

import java.util.ArrayList;

public interface HistoryManager {
    void add(Task task);
    String getHistory();
    ArrayList<Task> getHistoryList();


}
