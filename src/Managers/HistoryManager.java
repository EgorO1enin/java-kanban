package Managers;

import Tasks.Task;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


public interface HistoryManager {
    void add(Task task);
    List<Task> getHistory();
    void remove(int id);


}
