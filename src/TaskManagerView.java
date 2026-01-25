import java.util.List;

public class TaskManagerView {

    public void addTaskView() {
        System.out.println("Your task has been added!");
    }

    public void removeTaskView(Task task) {
        System.out.println(task + " has been removed from your list.");
    }

    public void completeTaskView(Task task, int remainingTasks) {
        if (remainingTasks > 0) {
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + task.getTitle() + " completed! " + ConsoleColors.CYAN_BOLD_BRIGHT +
             remainingTasks + " more tasks to go!" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + task.getTitle() + " completed! " + "\nYou have " +
             "completed all your tasks. Good job!" + ConsoleColors.RESET);
        }
    }

    public void setImportantTaskView() {
        System.out.println(ConsoleColors.YELLOW_BOLD_BRIGHT + "Task set as important!⭐");
    }

    public void getAllTasksView(List<Task> tasks) {
    System.out.println("To-do list: ");
    int userIndex = 1;
    if (tasks.isEmpty()) {
        System.out.println("No tasks in here!");
    } else {
        String format = " %5s | %25s | %15s | %10s %n"; //render
        System.out.printf(format, "ID", "TITLE", "CATEGORY", "STATUS");
        System.out.println("-------+---------------------------+-----------------+------------");
        for (Task t : tasks) {
            String status = t.isCompleted() ? "☑" : "☐";
            System.out.printf(format, userIndex++, t.getTitle(), t.getCategory(), status );
            }
        }
    }

    public void loadTasksView() {
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Your tasks are loaded!" + ConsoleColors.RESET);
    }


}