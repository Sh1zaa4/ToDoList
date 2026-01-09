import java.util.ArrayList;
import java.util.List;

//Qui posso creare l'arrayList e metodi che userò nel main nei vari use case.
public class TaskManager {
    static List<Task> tasks = new ArrayList<Task>();
    private TaskManagerView view = new TaskManagerView();

    public void addTask(Task task) {
        task.setId(tasks.size() +1); //alla creazione di ogni task, un id gli viene assegnato
        tasks.add(task);
        view.addTaskView();
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        view.removeTaskView(task);
    }

    public void completeTask(Task task) {
        task.setCompleted(true);
        tasks.remove(task);
        view.completeTaskView(task);
    }

    public void setImportantTask(Task task){
        task.setImportant(true);
        view.setImportantTaskView();
    }

    //TODO metodi per categorizzare le task, capire dove salvarle, etc!
}