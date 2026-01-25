import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) throws SQLException {

        DatabaseManager db = new DatabaseManager();
        db.connect();;
        TaskManager tm = new TaskManager(db);
        Scanner sc = new Scanner(System.in);
        tm.loadTasks();
        tm.printAllTasks();

        Task t = new Task("Shoppinggggggggggggggggggggggggggggggggggggggggggg");
        tm.addTask(t);
        tm.loadTasks();
        tm.printAllTasks();


        System.out.println("Insert id number of the task you completed: ");
        int UITaskNumber = sc.nextInt();

        int listNumber = UITaskNumber - 1;

        if (listNumber >= 0 && listNumber < tm.tasks.size()) {
            Task taskToComplete = tm.getTaskByIndex(listNumber);

            System.out.println("Hai selezionato: " + taskToComplete.getTitle());
            System.out.println("Il suo ID nascosto nel DB è: " + taskToComplete.getId());

            tm.completeTask(taskToComplete);
            sc.nextLine();

            System.out.println("Do you want to remove it from your list? (Y/n)");
            String inputRemove = sc.nextLine().trim();
            if(inputRemove.equalsIgnoreCase("y") || inputRemove.isEmpty()){
                tm.removeTask(taskToComplete); //TODO implement removeTaskDB
                System.out.println("The task has been removed!");
            } else {
                System.out.println("Task kept in the list.");
            }

        } else {
            System.out.println("Task isn't valid");
        }


        tm.loadTasks();
        tm.printAllTasks();

    }
}
