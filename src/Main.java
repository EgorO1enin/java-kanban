import Tasks.Epic;
import Managers.InMemoryHistoryManager;
import Managers.InMemoryTaskManager;
import Tasks.Subtask;
import Tasks.Task;

public class Main {

    public static void main(String[] args) {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

        //Создаем задачи!
        Epic epic1 = new Epic("Эпик 1", "Нужно сделать");
        int epic1Id = taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask("Subtask1 создания ",
                "Написать что то ", epic1Id);
        Subtask subtask2 = new Subtask("Subtask2 создания ",
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


        //Добавим задачи!
        taskManager.addSubtusk(subtask1);
        taskManager.addSubtusk(subtask2);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.addTask(task4);
        taskManager.addTask(task5);
        taskManager.addTask(task6);
        taskManager.addTask(task7);
        taskManager.addTask(task8);
        taskManager.addTask(task9);
        taskManager.addTask(task10);
        taskManager.addTask(task11);


       /* System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);*/
        System.out.println(taskManager.getEpicById(1));
        System.out.println(" ");
        /*System.out.println("History: ");
        historyManager.getHistory();
        System.out.println(" ");*/
        System.out.println(taskManager.getSubtaskById(2));
        /*System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();
        System.out.println(" ");*/
        System.out.println(taskManager.getSubtaskById(3));
       /* System.out.println(" ");
        System.out.println("History: ");

        historyManager.getHistory();*/
        //System.out.println(" ");
        System.out.println(taskManager.getTaskById(4));
       /* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();
        System.out.println(" ");*/
        System.out.println(taskManager.getTaskById(5));
       /* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();
        System.out.println(" ");*/
        System.out.println(taskManager.getTaskById(6));
       /* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();*/
        System.out.println(taskManager.getTaskById(7));
       /* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();*/
        System.out.println(taskManager.getTaskById(8));
       /* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();*/
        System.out.println(taskManager.getTaskById(9));
       /* System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();*/
        System.out.println(taskManager.getTaskById(10));
        /*System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();*/
        System.out.println(taskManager.getTaskById(11));
        System.out.println(" ");
        System.out.println("History: ");
        historyManager.getHistory();






       /* subtask1.setStatus(Tasks.Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask1);*/

        //taskManager.updateEpic(epic1);
        //taskManager.updateEpic(epic1);


       /* System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);*/


       /* subtask2.setStatus(Tasks.Status.DONE);
        taskManager.updateSubtask(subtask2);*/
        //taskManager.updateEpic(epic1);


      /*  System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);

        subtask1.setStatus(Tasks.Status.DONE);
        taskManager.updateSubtask(subtask1);*/
        //taskManager.updateEpic(epic1);


       /* System.out.println(epic1);
        System.out.println(subtask1);
        System.out.println(subtask2);*/

       /* System.out.println(taskManager.getSimpleTask());
        System.out.println(taskManager.getTask(4));

        System.out.println("!1111111111111111111111111111111");
        taskManager.getHistory();*/

    }


}







