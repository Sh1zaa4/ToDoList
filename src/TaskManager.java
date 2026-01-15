import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private DatabaseManager db;
    private TaskManagerView view = new TaskManagerView();

    //La connessione stabilita nel db è anche quella a cui accede il TaskManager
    public TaskManager(DatabaseManager db){
        this.db = db;
    }
    //metodo che si riferisce al db, ritorna feedback all'utente e controlla eccezioni
    public void addTask(Task task) {
        try {
            db.addTask(task); //la task viene aggiunta al db
            view.addTaskView();
        } catch (Exception e) {
            System.out.println("Failed to add task: " + e.getMessage());
        }
    }

    public void removeTask(Task task) {
        try {
            db.removeTask(task); //TODO da implementare
            view.removeTaskView(task);
        } catch (Exception e) {
            System.out.println("Failed to remove task: " + e.getMessage());
        }
    }

    public void completeTask(Task task) {
        task.setCompleted(true);
        db.removeTask(task);
        view.completeTaskView(task);
    }

    public void setImportantTask(Task task){
        task.setImportant(true);
        view.setImportantTaskView();
    }

    //TODO metodi per categorizzare le task, capire dove salvarle, etc!
}