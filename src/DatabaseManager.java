import java.sql.*;

public class DatabaseManager {
//TODO: -metodi per manipolare i task nel db (da usare nei metodi del taskManager);
//TODO: -countTask --> per contare i task da completare rimanenti, utile nel TaskManagerView (come report all'utente);

    //dati per connessione
    Connection connection = null;
    String dbServer = "";
    int dbPort = 3306;
    String dbName = "ginevra_todo";
    String user = "";
    String password = "";
    String url = String.format("",
            dbServer, dbPort, dbName, user, password);

    public Connection connect() throws SQLException {
        try{
            this.connection = DriverManager.getConnection(url,user,password);
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
                                "title VARCHAR(255) NOT NULL, "+
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
            preparedStatement.setString (2, task.getCategory().name());
            preparedStatement.setBoolean(3, task.isImportant());
            if(task.getDeadline() != null) {
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

    public void removeTask(Task task){

    }

//    public int countTasks() {
//        String countSQL = "SELECT COUNT(*) FROM tasks WHERE isCompleted = 0";
//
//        try {
//
//        }
    }

