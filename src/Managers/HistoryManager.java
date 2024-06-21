package Managers;

import Tasks.Task;

import java.util.ArrayList;

public interface HistoryManager {
    void add(Task task);
    void getHistory();
    ArrayList<Task> getHistoryList();


}
