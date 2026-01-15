import java.sql.SQLException;

public class Main {
    public static void main(String [] args) throws SQLException {

        DatabaseManager db = new DatabaseManager();
        db.connect();
        Task task = new Task("Homework");
        TaskManager tm = new TaskManager(db);
        tm.addTask(task);
        tm.completeTask(task);
    }
}
