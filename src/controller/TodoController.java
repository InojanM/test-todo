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

    public void printAllTodo() {

        ArrayList<TodoItem> todoItems = new ArrayList<>();
        todoItems = getAllTodoItems();
        for (int i = 0; i < todoItems.size(); i++) {
            System.out.println(todoItems.get(i));
        }
    }


    public void insertTodoItems(String title, int categoryId) throws SQLException {
        PreparedStatement preparedStatement = database.getPreparedStatement("INSERT INTO todo_items(title,category_id,completed) VALUES (?,?,?)");
        preparedStatement.setString(1, title);
        preparedStatement.setInt(2, categoryId);
        preparedStatement.setInt(3, 0);
        preparedStatement.execute();


    }

    public void printAllTodoByCategoryId() {

        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<TodoItem> todoItems = new ArrayList<>();

        categories = categoryController.getAllCategories();
        todoItems = getAllTodoItems();


        for (int i = 0; i < categories.size(); i++) {
            System.out.println(categories.get(i));

            for (int j = 0; j < todoItems.size(); j++) {

                if (todoItems.get(j).getCategoryID() == categories.get(i).getId()) {

                    if (todoItems.get(j).getCompleted() == 1) {

                        System.out.print("\t");
                        System.out.print("[X] ");
                        System.out.println(todoItems.get(j));
                    } else {
                        System.out.print("\t");
                        System.out.print("[ ]");
                        System.out.println(todoItems.get(j));
                    }

                }
            }
        }

    }


    public void setCompleted(int id) {
        try {
            PreparedStatement preparedStatement = database.getPreparedStatement("UPDATE todo_items SET completed = ? WHERE id=" + id);
            preparedStatement.setInt(1, 1);
            preparedStatement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
