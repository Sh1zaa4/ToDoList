
public class TaskManagerView {

    public void addTaskView() {
        System.out.println("Your task has been added!");
    }

    public void removeTaskView(Task task) {
        System.out.println(task + " has been removed from your list.");
    }

    public void completeTaskView(Task task) {
        System.out.println(task + " completed!");
        if (!TaskManager.tasks.isEmpty()) {
            System.out.println(TaskManager.tasks.size() + " tasks more to go!");
        } else {
            System.out.println("You have completed all your tasks! Good job!");
        }
    }

    public void setImportantTaskView() {
        System.out.println("Task set as important!⭐");
    }

}