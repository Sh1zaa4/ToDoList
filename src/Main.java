import java.sql.SQLException;

public class Main {
    public static void main(String [] args) throws SQLException {

        DatabaseManager db = new DatabaseManager();
        db.connect();
//        Task task = new Task("Shower");
//        Task task2 = new Task("Homeworkk");
        TaskManager tm = new TaskManager(db);
//        tm.addTask(task);
//        tm.completeTask(task);

        tm.loadTasks();

        Task taskToComplete = tm.getIdTask(3);
        if (taskToComplete != null) {
            tm.completeTask(taskToComplete);
        } else {
            System.out.println("task inesistente");
        }

        tm.printAllTasks();

    }
}
