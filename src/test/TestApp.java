package test;

import controller.CategoryController;
import controller.TodoController;
import db.Database;

import java.sql.SQLException;

public class TestApp {

    public static void main(String[] args) throws SQLException {

        Database database = new Database("mytodo.db");


        TodoController todoController = new TodoController(database);
        todoController.setCompleted(5);

    }
}
