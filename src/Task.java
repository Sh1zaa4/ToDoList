import java.util.Date;

public class Task {
    // TODO add description
    private int id;
    private String title;
    private Category category; // my tasks (default), work, school, home
    private boolean completed;
    private boolean important;
    private Date deadline;
    private Date createdAt;

    //constructors
    //costruttore 'nuovo task' (per l'utente) --- costruttore 'piccolo', quindi chiama quello più grande (il totale)
    public Task(String title){
        this(title, -1, Category.MYTASKS, false, false, null);
    }

    //costruttore 'nuovo task' alternativo (per l'utente) --- constructor chaining
    public Task(String title, Category category){
        this(title, -1, category, false, false, null); // aggiungo createdAt
    }

    //costruttore 'totale' (per caricamento da db)
    public Task(String title, int id, Category category, boolean completed, boolean important, Date deadline){
        this.id = id;
        this.title = title;
        this.category = category;
        //partono con valori nulli, saranno poi modificabili successivamente
        this.completed = completed;
        this.important = important;
        this.deadline = deadline;
        this.createdAt = new Date();
    }



    //getter
    public String getTitle() { return title; }

    public int getId() { return id; }

    public Category getCategory() { return category; }

    public boolean isCompleted() { return completed; }

    public boolean isImportant() { return important; }

    public Date getDeadline() { return deadline; }

    //setter
    public void setTitle(String title) { this.title = title; }

    public void setId(int id) { this.id = id; }

    public void setCategory(Category category) { this.category = category; }

    public void setCompleted(boolean completed) { this.completed = completed; }

    public void setImportant(boolean important) { this.important = important; }

    public void setDeadline(Date deadline) { this.deadline = deadline; }
}

