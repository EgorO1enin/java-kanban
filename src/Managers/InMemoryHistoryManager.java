package Managers;

import Tasks.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {
    private static ArrayList<Task> historyList = new ArrayList<>();

    @Override
    public  ArrayList<Task> getHistoryList() {
        return new ArrayList<>(historyList);
    }

    @Override
    public void add(Task task) {
        if (historyList.size() == 10) {
            historyList.remove(0);
        }
        historyList.add(task);
    }

    @Override
    public void getHistory() {
        if (historyList.isEmpty()){
            System.out.println("empty");
        }
        for (int i = 0; i < historyList.size(); i++) {
            System.out.println(historyList.get(i));
        }

    }

}
