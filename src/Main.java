import managers.*;
import task.Epic;
import task.Subtask;
import task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

//Реализация для FileBackedTaskManager
public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        InMemoryTaskManager taskManager = new InMemoryTaskManager();
        InMemoryHistoryManager historyManager = new InMemoryHistoryManager();
        File file = new File("text.csv");
        FileBackedTaskManager fileBackedTaskManager = FileBackedTaskManager.load(file);
        //Создаем задачи
        LocalDate today1 = LocalDate.now();
        LocalTime startTime1 = LocalTime.parse("11:02");
        /*task1.setStartTime(LocalDateTime.of(today1, startTime1));
        task1.setDuration(Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task1);*/
        Task task1 = new Task("Test 1", "To be there at 3 AM", LocalDateTime.of(today1, startTime1), Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task1);
        //fileBackedTaskManager.addTaskToSortedTreeSet(task1);

        LocalDate today2 = LocalDate.now();
        LocalTime startTime2 = LocalTime.parse("11:00");
        /*task1.setStartTime(LocalDateTime.of(today1, startTime1));
        task1.setDuration(Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task1);*/
        Task task2 = new Task("Test 2", "To be there at 3 AM", LocalDateTime.of(today2, startTime2), Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task2);
       // fileBackedTaskManager.addTaskToSortedTreeSet(task2);


        LocalDate today3 = LocalDate.now();
        LocalTime startTime3 = LocalTime.parse("12:00");
        /*task1.setStartTime(LocalDateTime.of(today1, startTime1));
        task1.setDuration(Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task1);*/
        Task task3 = new Task("Test 3", "To be there at 3 AM", LocalDateTime.of(today3, startTime3), Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task3);

        LocalDate today4 = LocalDate.now();
        LocalTime startTime4 = LocalTime.parse("13:00");
        /*task1.setStartTime(LocalDateTime.of(today1, startTime1));
        task1.setDuration(Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task1);*/
        Task task4 = new Task("Test 4", "To be there at 3 AM", LocalDateTime.of(today4, startTime4), Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task4);

        //Начинаем созжаваь эпик
        Epic epic1 = new Epic("Fix Bug", "this bug is on main page of site");

        LocalDate today5 = LocalDate.now();
        LocalTime startTime5 = LocalTime.parse("15:00");
        /*task1.setStartTime(LocalDateTime.of(today1, startTime1));
        task1.setDuration(Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task1);*/
        Subtask subtask1 = new Subtask("To solve the problem with payment",
                "Description", epic1.getId(), LocalDateTime.of(today5, startTime5), Duration.ofMinutes(10));
        LocalDate today6 = LocalDate.now();
        LocalTime startTime6 = LocalTime.parse("15:00");
        /*task1.setStartTime(LocalDateTime.of(today1, startTime1));
        task1.setDuration(Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task1);*/
        Subtask subtask2 = new Subtask("To solve the problem with payment2",
                "Description", epic1.getId(), LocalDateTime.of(today6, startTime6), Duration.ofMinutes(40));

        epic1.setStartTime(subtask1.getStartTime());
        epic1.setEndTime(subtask2.getEndTime());
        //fileBackedTaskManager.inicializeDurationOfEpic(epic1);
        fileBackedTaskManager.initEpicDuration(epic1, subtask1, subtask2);
        //epic1.setDuration((Duration.between(subtask1.getStartTime(), subtask2.getEndTime())));

        if (fileBackedTaskManager.addEpic(epic1) != 0) {
            fileBackedTaskManager.addEpic(epic1);
            subtask1.setEpicId(epic1.getId());
            subtask2.setEpicId(epic1.getId());
        /*System.out.println(subtask1.getEpicId());
        System.out.println(subtask2.getEpicId());*/
            fileBackedTaskManager.addSubtusk(subtask1);
            fileBackedTaskManager.addSubtusk(subtask2);
        } else {
            System.out.println("Из за наложения эпика неавозможно добавить его");
        }
        System.out.println(fileBackedTaskManager.getSimpleTaskList());
        System.out.println(fileBackedTaskManager.getEpicTaskList());
        System.out.println(fileBackedTaskManager.getSubTaskList());
        fileBackedTaskManager.printSortedTaskList();



       /* Task task2 = new Task("Have a breakfast", "Meat is in the fridge");
        LocalDate today2 = LocalDate.now();
        LocalTime startTime2 = LocalTime.parse("11:00");
        task2.setStartTime(LocalDateTime.of(today2, startTime2));
        task2.setDuration(Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task2);


        Task task3 = new Task("To go for meetup", "No information about meetup");
        LocalDate today3 = LocalDate.now();
        LocalTime startTime3 = LocalTime.parse("10:00");
        task3.setStartTime(LocalDateTime.of(today3, startTime3));
        task3.setDuration(Duration.ofMinutes(10));
        fileBackedTaskManager.addTask(task3);

        Epic epic1 = new Epic("Fix Bug", "this bug is on main page of site");
        //fileBackedTaskManager.addEpic(epic1);

        Subtask subtask1 = new Subtask("To solve the problem with payment",
                "No info how to do it", epic1.getId());
        LocalDate today4 = LocalDate.now();
        LocalTime startTime4 = LocalTime.parse("10:00");
        subtask1.setStartTime(LocalDateTime.of(today4, startTime4));
        subtask1.setDuration(Duration.ofMinutes(10));
        //fileBackedTaskManager.addSubtusk(subtask1);

        Subtask subtask2 = new Subtask("To solve the problem with region",
                "No info how to do it", epic1.getId());
        LocalDate today5 = LocalDate.now();
        LocalTime startTime5 = LocalTime.parse("10:00");
        subtask2.setStartTime(LocalDateTime.of(today5, startTime5));
        subtask2.setDuration(Duration.ofMinutes(10));
        //fileBackedTaskManager.addSubtusk(subtask2);

        Subtask subtask3 = new Subtask("To solve the problem with log page",
                "No info how to do it", epic1.getId());
        LocalDate today6 = LocalDate.now();
        LocalTime startTime6 = LocalTime.parse("10:00");
        subtask3.setStartTime(LocalDateTime.of(today6, startTime6));
        subtask3.setDuration(Duration.ofMinutes(10));
        //fileBackedTaskManager.addSubtusk(subtask3);

        fileBackedTaskManager.addEpic(epic1);
        fileBackedTaskManager.addSubtusk(subtask1);
        fileBackedTaskManager.addSubtusk(subtask2);
        fileBackedTaskManager.addSubtusk(subtask3);
        epic1.setStartTime();
        //fileBackedTaskManager.addEpic(epic1);

        System.out.println(fileBackedTaskManager.getSimpleTaskList());
        System.out.println(fileBackedTaskManager.getEpicTaskList());
        System.out.println(fileBackedTaskManager.getSubTaskList());
        //Создаем задачи!
*/
    }
}









