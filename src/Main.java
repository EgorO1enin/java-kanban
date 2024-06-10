import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        ArrayList<Integer> indexOfSubtaskInEpic = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Scanner scannerForString = new Scanner(System.in);

        int cmd = -1;
        while (cmd != 0) {
            System.out.println();
            System.out.println("1 - Ввести простую задачу");
            System.out.println("2 - Ввести эпик задачу");
            System.out.println("3 - Вывод всех задач на экран");
            System.out.println("4 - Вывод всех подзадач эпика");
            System.out.println("5 - Изменение статуса задачи");
            System.out.println("6 - Удалить все задачи");
            System.out.println("7 - Удалить задачу по индеску");
            System.out.println("0 - Выход");
            cmd = scanner.nextInt();
            if (cmd == 1) {
                System.out.println("Введите название Задачи:");
                String task = scannerForString.nextLine();
                System.out.println("Введите описание задачи: ");
                String description = scannerForString.nextLine();
                Task task1 = new Task(task, description);
                taskManager.addTask(task1);
                System.out.println("Задача успешно добавлена!");
            } else if (cmd == 2) {
                System.out.println("Введите название основного Задания");
                String epicTask = scannerForString.nextLine();
                System.out.println("Введите описание задачи: ");
                String description = scannerForString.nextLine();
                Epic task = new Epic(epicTask, description);
                taskManager.addEpic(task);
                System.out.println("Сколько вы хотите добавить доп заданий?");
                int ammountOfTasks = scanner.nextInt();
                for (int i = 0; i < ammountOfTasks; i++) {
                    System.out.println("Введите название вторичного задания " + (i + 1));
                    String subtaskName = scannerForString.nextLine();
                    System.out.println("Введите описание задачи: ");
                    String descriptionForSubtasks = scannerForString.nextLine();
                    Subtusk subtusk = new Subtusk(subtaskName, descriptionForSubtasks, task.getId());
                    indexOfSubtaskInEpic.add(taskManager.taskId);
                    task.setSubTaskList(indexOfSubtaskInEpic);
                    taskManager.addSubtusk(subtusk);
                }
                indexOfSubtaskInEpic = new ArrayList<>();
                System.out.println("Задача успешно добавлена!");
            } else if (cmd == 3) {
                int count = 1;
                System.out.println("Список всех Задач:");
                for (int i = 1; i <= taskManager.taskId - 1; i++) {
                    if (taskManager.getSimpleTask().containsKey(i)) {
                        System.out.print(count + " ");
                        count++;
                        System.out.println(taskManager.getSimpleTask().get(i));
                        System.out.println("Описание задачи: ");
                        System.out.println(taskManager.getSimpleTask().get(i).getDescription());
                        System.out.println();
                    } else if (taskManager.getEpicTask().containsKey(i)) {
                        System.out.print(count + " ");
                        count++;
                        System.out.println(taskManager.getEpicTask().get(i));
                        System.out.println("Описание задачи: ");
                        System.out.println(taskManager.getEpicTask().get(i).getDescription());
                        for (int j : taskManager.getEpicTask().get(i).getSubTaskList()) {
                            if (taskManager.getSubTusk().containsKey(j)) {
                                System.out.println(taskManager.getSubTusk().get(j));
                                System.out.println("Описание задачи: ");
                                System.out.println(taskManager.getSubTusk().get(j).getDescription());
                            }
                        }
                        System.out.println();
                    }
                }
                if (count == 1){
                    System.out.println("Список пуст!");
                }
            }else if (cmd == 4){
                System.out.println("Введите айди Эпика для которого хотите вывести подзадачи");
                int id = scanner.nextInt();
                if (taskManager.getEpicTask().get(id).getSubTaskList().isEmpty()){
                    System.out.println("В данном эпике нет подзадач!");
                } else {
                    for (int j : taskManager.getEpicTask().get(id).getSubTaskList()) {
                        if (taskManager.getSubTusk().containsKey(j)) {
                            System.out.println(taskManager.getSubTusk().get(j));
                            System.out.println(taskManager.getSubTusk().get(j).getDescription());
                        }
                    }
                }
            } else if (cmd == 5) {
                System.out.println("Статус какой задачи вы хотите поменть? Введите айди задачи: ");
                int changeTaskId = scanner.nextInt();
                System.out.println("Статусы бывают: New, In progress, Done. Введите новый статус задачи: ");
                String inputStatus = scannerForString.nextLine();
                Status status = Status.NEW;
                if (inputStatus.equals("In progress")) {
                    status = Status.IN_PROGRESS;
                } else if (inputStatus.equals("Done")) {
                    status = Status.DONE;
                }
                if (taskManager.getSimpleTask().containsKey(changeTaskId)) {
                    taskManager.changeStatusOfSimpleTask(changeTaskId, status);
                    System.out.println("Статус успешно изменен!");
                } else if (taskManager.getSubTusk().containsKey(changeTaskId)) {
                    taskManager.changeStatusOfSubTask(changeTaskId, status);
                    System.out.println("Статус успешно изменен!");
                } else if (taskManager.getEpicTask().containsKey(changeTaskId)) {
                    if (taskManager.getEpicTask().get(changeTaskId).getSubTaskList().isEmpty()) {
                        taskManager.changeStatusOfEpiceTask(changeTaskId, status);
                        System.out.println("Статус успешно изменен!");
                    } else {
                        System.out.println("В ЭПИК задаче есть подзадачи," +
                                " вы не можете изменить статус ЭПИКА пока не измените статус подзадачи!");
                    }
                } else {
                    System.out.println("Задача с таким айди не найдена!");
                }
            } else if (cmd == 6){
                taskManager.deleteAllTasks();
                System.out.println("Все задачи из списка удалены!");
                System.out.println("Список пуст");
            } else if (cmd == 7) {
                System.out.println("Введите индекс задачи которую надо удалить: ");
                int index = scanner.nextInt();
                taskManager.deleteTaskByInd(index);
            } else if (cmd == 0) {
                System.out.println("Досвидания!");
                break;
            }
        }
    }
}






