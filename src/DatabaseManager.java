import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
//TODO: -metodi per manipolare i task nel db (da usare nei metodi del taskManager);

    //dati per connessione
    Connection connection = null;
    String dbServer = "";
    int dbPort = 3306;
    String dbName = "ginevra_todo";
    String user = "";
    String password = "";
    String url = "jdbc:mariadb://" + dbServer + ":" + dbPort + "/" + dbName;

    public Connection connect() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + "Successful connection to the database!" +
                    ConsoleColors.RESET);
        } catch (SQLException e) {
            System.out.println(ConsoleColors.RED_BOLD_BRIGHT + "Failed database connection: " + e.getMessage() +
                    ConsoleColors.RESET);
        }
        return connection;
    }

    public void createTable() throws SQLException {

        String createTableSQL = "CREATE TABLE IF NOT EXISTS tasks" +
                "(id INT AUTO_INCREMENT PRIMARY KEY, " +
                "title VARCHAR(255) NOT NULL, " +
                "category VARCHAR(50) DEFAULT 'MyTasks', " +
                "isImportant BOOLEAN NOT NULL DEFAULT 0, " +
                "deadline DATE DEFAULT NULL, " +
                "createdAt DATE NOT NULL DEFAULT CURRENT_TIMESTAMP(), " +
                "isCompleted BOOLEAN NOT NULL DEFAULT 0)";
        try {
            Statement statement = connection.createStatement();
            statement.execute(createTableSQL);
            System.out.println("'tasks' table created or already existed.");
        } catch (SQLException e) {
            System.out.println("Failed table creation " + e.getMessage());
        }
    }

    public void addTask(Task task) {

        String addTaskSQL = "INSERT INTO tasks (title, category, isImportant, deadline," +
                " isCompleted) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(addTaskSQL);
            preparedStatement.setString(1, task.getTitle());
            preparedStatement.setString(2, task.getCategory().name());
            preparedStatement.setBoolean(3, task.isImportant());
            if (task.getDeadline() != null) {
                preparedStatement.setDate(4, new java.sql.Date(task.getDeadline().getTime()));
            } else {
                preparedStatement.setNull(4, Types.DATE);
            }
            preparedStatement.setBoolean(5, task.isCompleted());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to insert task " + e.getMessage());
        }
    }

    public void removeTask(Task task) {

    }

    public void setCompletedTask(Task task) {
        String importantTasksSQL = "UPDATE tasks SET isCompleted = 1 WHERE id = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(importantTasksSQL);
            preparedStatement.setInt(1, task.getId()); //id preso da riferimento a getIdTask

            preparedStatement.executeUpdate();
            System.out.println("Task aggiornata nel database!");
        } catch (SQLException e) {
            System.out.println("Failed to insert task " + e.getMessage());
        }
    }

    public List<Task> getAllTasks() throws SQLException {

        String allTasksSQL = "SELECT id, title, category, isCompleted FROM tasks";
        List<Task> tasks = new ArrayList<Task>();

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(allTasksSQL)) {
            while (rs.next()) {
                Task task = new Task(rs.getString("title"));
                task.setId(rs.getInt("id"));
                task.setCategory(Category.valueOf(rs.getString("category")));
                task.setCompleted(rs.getBoolean("isCompleted"));

                tasks.add(task);
            }
        }
        return tasks;
    }


    public int countTasks() throws SQLException {

        String countSQL = "SELECT COUNT(*) FROM tasks WHERE isCompleted = 0"; //il db restituirà 1 riga con il
        // numero di task non completate
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(countSQL)) {

            while (rs.next()) {
                return rs.getInt(1);
            } //restituisce numero trovato nella colonna
        }
        return 0;
    }
}

