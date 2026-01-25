import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private DatabaseManager db;
    private TaskManagerView view = new TaskManagerView();
    List<Task> tasks = new ArrayList<>();

    //La connessione stabilita nel db è anche quella a cui accede il TaskManager
    public TaskManager(DatabaseManager db){
        this.db = db;
    }

    public void addTask(Task task) {
        try {
            db.addTaskDB(task); //la task viene aggiunta al db
            view.addTaskView();
        } catch (Exception e) {
            System.out.println("Failed to add task: " + e.getMessage());
        }
    }

    public void removeTask(Task task) {
        try {
            db.removeTaskDB(task); //TODO da implementare
            view.removeTaskView(task);
        } catch (Exception e) {
            System.out.println("Failed to remove task: " + e.getMessage());
        }
    }

    public void completeTask(Task task) throws SQLException {
        if (!task.isCompleted()) {
            task.setCompleted(true);
            db.setCompletedTaskDB(task);
            //db.removeTask(task);
            view.completeTaskView(task, db.countTasks());
        } else {
            System.out.println(ConsoleColors.WHITE_UNDERLINED + "Task is already completed!" + ConsoleColors.RESET);
        }
    }

    public void setImportantTask(Task task){
        task.setImportant(true);
        view.setImportantTaskView();
    }

    public Task getTaskByIndex(int id) {
        if(id >= 0 && id < tasks.size()){
            return tasks.get(id);
        } else {
            System.out.println("Insert a valid ID");
            return null;
        }
    }

    public void loadTasks(){
        try{
            this.tasks = db.getAllTasks(); //salvo i dati recuperati dal db in una lista temporanea
            view.loadTasksView();
        } catch (SQLException e) {
            System.out.println("Failed to load tasks " + e.getMessage());
        }
    }

    public void printAllTasks(){
        view.getAllTasksView(this.tasks);
        System.out.println();
    }

    //TODO metodi per categorizzare le task, capire dove salvarle, etc!
}