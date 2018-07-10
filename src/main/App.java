package main;

import controller.DbBootstrapController;
import controller.TodoController;
import db.Database;

public class App {

    private String dbFile;

    public static void main(String[] args) {

        App app = new App();
        app.setDbFile("myTodo.db");

        if (Database.fileExists(app.getDbFile())) {
            app.initTodoApplication();
        } else {
            /*
             * If database file doesn't exist, then create a new file,
             * and init default database structures.
             */
            app.initEmptyDatabase(app.getDbFile());

        }

    }

    private void setDbFile(String dbFile) {
        this.dbFile = dbFile;
    }

    private String getDbFile() {
        return this.dbFile;
    }

    private void initEmptyDatabase(String dbFile) {
        DbBootstrapController dbBootstrapController = new DbBootstrapController(new Database(dbFile));
        boolean result = dbBootstrapController.initTodoDatabase();

        if (result)
            System.out.println("Todo database init... OK");
        else
            System.out.println("Todo database init.. FAILED");
    }

    /**
     * This is the main application logic bootstrap area.
     */
    private void initTodoApplication() {
        /* DEBUG */
        System.out.println("Database file..OK");

        Database database = new Database(dbFile);

        TodoController todoController = new TodoController(database);

        System.out.println(todoController.getAllTodoItems());

    }

}
