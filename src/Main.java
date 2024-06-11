import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Epic epic1 = new Epic("Эпик 1","Нужно сделать");
        int epic1Id = taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask("Subtask1 создания ",
                "Написать что то ", epic1Id);
        Subtask subtask2 = new Subtask("Subtask2 создания ",
                "Написать что то ", epic1Id);
        taskManager.addSubtusk(subtask1);
        taskManager.addSubtusk(subtask2);


        System.out.println(epic1);
        /*System.out.println(subtask1);
        System.out.println(subtask2);*/


        subtask1.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubtask(subtask1);

        //taskManager.updateEpic(epic1);
        //taskManager.updateEpic(epic1);



        System.out.println(epic1);
      /*  System.out.println(subtask1);
        System.out.println(subtask2);*/


        subtask2.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask2);
        //taskManager.updateEpic(epic1);


        System.out.println(epic1);
        /*System.out.println(subtask1);
        System.out.println(subtask2);*/

        subtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask1);
        //taskManager.updateEpic(epic1);



        System.out.println(epic1);
        /*System.out.println(subtask1);
        System.out.println(subtask2);*/

    }
}






