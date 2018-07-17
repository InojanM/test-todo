package controller;

import db.Database;
import model.Category;
import model.TodoItem;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TodoController {

    private Database database;
    private CategoryController categoryController;

    public TodoController(Database db) {
        this.database = db;
        this.categoryController = new CategoryController(database);
    }

    public ArrayList<TodoItem> getAllTodoItems() {

        ArrayList<TodoItem> items = new ArrayList<>();

        ResultSet resultSet = database.executeQuery("SELECT * FROM todo_items");

        try {
            while (resultSet.next()) {
                TodoItem todoItem = new TodoItem(
                        resultSet.getInt(TodoItem.ID),
                        resultSet.getString(TodoItem.TITLE),
                        resultSet.getInt(TodoItem.CATEGORY_ID),
                        resultSet.getInt(TodoItem.COMPLETED)
                );

                Category category = categoryController.getCategoryByID(todoItem.getCategoryID());
                todoItem.setCategory(category);

                items.add(todoItem);
            }

            return items;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return null;
    }

    public void insertTodoItems(String title, int categoryId) throws SQLException {
        PreparedStatement preparedStatement = database.getPreparedStatement("INSERT INTO todo_items(title,category_id,completed) VALUES (?,?,?)");
        preparedStatement.setString(1, title);
        preparedStatement.setInt(2, categoryId);
        preparedStatement.setInt(3, 0);
        preparedStatement.execute();

    }
    
}
