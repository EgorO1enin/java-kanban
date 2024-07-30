import managers.FileBackedTaskManager;
import task.Epic;
import managers.InMemoryHistoryManager;
import managers.InMemoryTaskManager;
import task.Subtask;
import task.Task;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        File file = new File("text.csv");
        FileBackedTaskManager fileBackedTaskManager = FileBackedTaskManager.load(file);
        //Создаем задачи
        Task task1 = new Task("Go to the gym", "To be there at 3 AM");
        fileBackedTaskManager.addTask(task1);
        Task task2 = new Task("Have a breakfast", "Meat is in the fridge");
        fileBackedTaskManager.addTask(task2);
        Task task3 = new Task("To go for meetup", "No information about meetup");
        fileBackedTaskManager.addTask(task3);
        Epic epic1 = new Epic("Fix Bug", "this bug is on main page of site");
        fileBackedTaskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask("To solve the problem with payment",
                "No info how to do it", epic1.getId());
        fileBackedTaskManager.addSubtusk(subtask1);
        Subtask subtask2 = new Subtask("To solve the problem with region",
                "No info how to do it", epic1.getId());
        fileBackedTaskManager.addSubtusk(subtask2);
        Subtask subtask3 = new Subtask("To solve the problem with log page",
                "No info how to do it", epic1.getId());
        fileBackedTaskManager.addSubtusk(subtask3);

        //Добавляем задачи
        //fileBackedTaskManager.addTask(task1);
        //fileBackedTaskManager.addTask(task2);
        //fileBackedTaskManager.addTask(task3);

        //fileBackedTaskManager.addSubtusk(subtask1);
        //fileBackedTaskManager.addSubtusk(subtask2);
        //fileBackedTaskManager.addSubtusk(subtask3);
        //System.out.println(fileBackedTaskManager.getSimpleTaskList());


        /*System.out.println("History befor:");
        for (int i = 0; i < fileBackedTaskManager.getAllHistory().size(); i++) {
            System.out.println(fileBackedTaskManager.getAllHistory().get(i));
        }
        System.out.println("History After:");
        fileBackedTaskManager.removeTaskFromHistory(1);
        for (int i = 0; i < fileBackedTaskManager.getAllHistory().size(); i++) {
            System.out.println(fileBackedTaskManager.getAllHistory().get(i));
        }
*/


        System.out.println(fileBackedTaskManager.getSimpleTaskList());
        System.out.println(fileBackedTaskManager.getEpicTaskList());
        System.out.println(fileBackedTaskManager.getSubTaskList());





        //Создаем задачи!
        /*Epic epic1 = new Epic("Эпик 1", "Нужно сделать");
        int epic1Id = taskManager1.addEpic(epic1);
        Subtask subtask1 = new Subtask("Subtask1 создания",
                "Написать что то ", epic1Id);
        Subtask subtask2 = new Subtask("Subtask2 создания",
                "Написать что то ", epic1Id);
        Task task1 = new Task("Tasks.Task 1", "testing task");
        Task task2 = new Task("Tasks.Task 2", "testing task");
        Task task3 = new Task("Tasks.Task 3", "testing task");
        Task task4 = new Task("Tasks.Task 4", "testing task");
        Task task5 = new Task("Tasks.Task 5", "testing task");
        Task task6 = new Task("Tasks.Task 6", "testing task");
        Task task7 = new Task("Tasks.Task 7", "testing task");
        Task task8 = new Task("Tasks.Task 8", "testing task");
        Task task9 = new Task("Tasks.Task 9", "testing task");
        Task task10 = new Task("Tasks.Task 10", "testing task");
        Task task11 = new Task("Tasks.Task 11", "testing task");
        Epic epic12 = new Epic("testEpic", "ысфсвфвс");
        Subtask subtask12 = new Subtask("Subtask2 создания",
                "ytguyit6yu ", taskManager1.addEpic(epic12));


        //Добавим задачи!
        taskManager1.addSubtusk(subtask1);
        taskManager1.addSubtusk(subtask2);
        taskManager1.addTask(task1);
        taskManager1.addTask(task2);
        taskManager1.addTask(task3);
        taskManager1.addTask(task4);
        taskManager1.addTask(task5);
        taskManager1.addTask(task6);
        taskManager1.addTask(task7);
        taskManager1.addTask(task8);
        taskManager1.addTask(task9);
        taskManager1.addTask(task10);
        taskManager1.addTask(task11);
        taskManager1.addEpic(epic12);
        taskManager1.addSubtusk(subtask12);


       *//* System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);*//*
        System.out.println(taskManager1.getEpicById(1));
        *//*System.out.println("History: ");
        historyManager.getHistory();
        System.out.println(" ");*//*
        System.out.println(taskManager1.getSubtaskById(2));
        *//*System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();
        System.out.println(" ");*//*
        System.out.println(taskManager1.getSubtaskById(3));
       *//* System.out.println(" ");
        System.out.println("History: ");

        historyManager.getHistory();*//*
        //System.out.println(" ");
        System.out.println(taskManager1.getTaskById(4));
       *//* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();
        System.out.println(" ");*//*
        System.out.println(taskManager1.getTaskById(5));
       *//* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();
        System.out.println(" ");*//*
        System.out.println(taskManager1.getTaskById(6));
       *//* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();*//*
        System.out.println(taskManager1.getTaskById(7));
       *//* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();*//*
        System.out.println(taskManager1.getTaskById(8));
       *//* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();*//*
        System.out.println(taskManager1.getTaskById(9));
       *//* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();*//*
        System.out.println(taskManager1.getTaskById(10));
        *//*System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();*//*
        System.out.println(taskManager1.getTaskById(11));
        System.out.println(taskManager1.getEpicById(12));
        System.out.println(taskManager1.getSubtaskById(13));

        //System.out.println(historyManager.getHistoryList());
        System.out.println("History befor:");
        for (int i = 0; i < taskManager1.getAllHistory().size(); i++) {
            System.out.println(taskManager1.getAllHistory().get(i));
        }
        System.out.println("History After:");
        taskManager1.removeTaskFromHistory(1);
        for (int i = 0; i < taskManager1.getAllHistory().size(); i++) {
            System.out.println(taskManager1.getAllHistory().get(i));
        }





      *//*  Task task12 = new Task("task1", "Task 1");
        Task task22 = new Task("task2", "Task 2");
        Task task32 = new Task("task3", "Task 3");

        historyManager.add(task12);
        historyManager.add(task22);
        historyManager.add(task32);
        System.out.println(historyManager.getHistory());
        System.out.println(historyManager.getHistory().size());*//*


         *//* if(historyManager.mapOfTasks.isEmpty()){
            System.out.println("null");
        }
        System.out.println(historyManager.getAllTasks());
*//*






         *//* subtask1.setStatus(Tasks.Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask1);*//*

        //taskManager.updateEpic(epic1);
        //taskManager.updateEpic(epic1);


       *//* System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);*//*


         *//* subtask2.setStatus(Tasks.Status.DONE);
        taskManager.updateSubtask(subtask2);*//*
        //taskManager.updateEpic(epic1);


      *//*  System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);

        subtask1.setStatus(Tasks.Status.DONE);
        taskManager.updateSubtask(subtask1);*//*
        //taskManager.updateEpic(epic1);


       *//* System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);*//*

         *//* System.out.println(taskManager.getSimpleTask());
        System.out.println(taskManager.getTask(4));

        System.out.println("!1111111111111111111111111111111");
        taskManager.getHistory();*//*

    }

*/
    }
}







