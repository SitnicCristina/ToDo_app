package md.tekwill.console;

import md.tekwill.task.Task;
import md.tekwill.task.TaskManager;

import java.util.Scanner;

public class ConsoleMenu {
    private TaskManager taskManager;
    private Scanner scanner;

    public ConsoleMenu() {
        taskManager = new TaskManager();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean exitProgram = false;
        do {
            System.out.println("\t\tWelcome to TODO List App");
            System.out.println("Available options:");
            System.out.println("[1] View all tasks ");
            System.out.println("[2] Add new tasks ");
            System.out.println("[3] Delete a tasks ");
            System.out.println("[4] Update a tasks ");
            System.out.println("[0] Exit ");
            System.out.print(">> ");

            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    showAllTasks();
                    break;
                case 2:
                    addNewTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    updateTask();
                    break;
                case 0:
                    System.out.println("Tha app is closed. BYE ");
                    exitProgram = true;
                    break;
                default:
                    System.out.println("Your option is wrong! Please enter again: \n");
                    break;
            }
        } while (!exitProgram);
    }

    private boolean handleUserChoice(int userChoice) {
        boolean flag = true;
        return flag;
    }

    private void showAllTasks() {
        Task[] tasks = taskManager.getAll();
        System.out.println("All tasks:");
        if (tasks.length == 0) {
            System.out.println("No task yet! \n");
        } else {
            for (Task task : tasks) {
                System.out.println("\t[" + task.getId() + "]" + task.getTitle());
                System.out.println("\tDescription: " + task.getDescription());
                System.out.println("\tTarget date: " + task.getTargetDate());
                System.out.println("\tDone: " + task.isDone() + "\n");
            }
        }
    }

    private void addNewTask() {
        System.out.println("Input unique ID (integer) for this task: ");
        int newId = scanner.nextInt();
        System.out.println("Insert a title for your task: ");
        scanner.nextLine();
        String newTitle = scanner.nextLine();
        System.out.println("Provide a description of your task: ");
        String newDescription = scanner.nextLine();
        System.out.println("Insert the target for your task: ");
        String newTargetDate = scanner.nextLine();

        taskManager.add(new Task(newId, newTitle, newDescription, newTargetDate));
    }

    private void deleteTask() {
        System.out.println("Input the ID of task to delete: ");
        int newId = scanner.nextInt();
        showAllTasks();
        taskManager.delete(newId);
    }

    private void updateTask() {
        showAllTasks();
        System.out.println("Input the ID of task to edit: ");
        int newId = scanner.nextInt();
        System.out.println("Is the task done? (accepted values are: true | false ) ");
        boolean newStatus = scanner.nextBoolean();
        taskManager.update(newId, newStatus);
    }
}
