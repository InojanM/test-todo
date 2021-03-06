package main;

import controller.CategoryController;
import controller.DbBootstrapController;
import controller.TodoController;
import db.Database;
import helper.HelperFunction;
import menu.AppMenu;

import java.sql.SQLException;
import java.util.Scanner;

public class App {

    private String dbFile;
    HelperFunction helperFunction = new HelperFunction();
    AppMenu appMenu = new AppMenu();

    public static void main(String[] args) throws SQLException {

        App app = new App();
        app.setDbFile("myTodo.db");

        if (Database.fileExists(app.getDbFile())) {

            app.appMenu();
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


    public void appMenu() throws SQLException {




        boolean condition = true;

        Scanner scanner = new Scanner(System.in);


        while (condition) {


            Database database = new Database(dbFile);


            TodoController todoController = new TodoController(database);

            CategoryController categoryController = new CategoryController(database);

            appMenu.menu();
            System.out.print("Enter your choice: ");
            int choice = helperFunction.intRangeValidate(0, 10, helperFunction.intNumberValidate(scanner.nextLine()));

            if (choice == 1) {

                System.out.println("Enter new category name: ");
                String category = scanner.nextLine();
                categoryController.insertCategory(category);

                categoryController.printAllCategories();

                System.out.println();

            } else if (choice == 2) {

                System.out.print("Enter your todo item: ");
                String title = scanner.nextLine();

                System.out.println();

                categoryController.printAllCategories();
                System.out.println();
                System.out.print("Choose a category number: ");
                int category_number = scanner.nextInt();

                todoController.insertTodoItems(title, category_number);

                todoController.printAllTodo();

            } else if (choice == 3) {

                todoController.printAllTodoByCategoryId();
                System.out.println();

            } else if (choice == 4) {

                System.out.println("Choose a completed todo blow todo list");
                System.out.println();
                todoController.printAllTodo();
                System.out.println();
                int id = helperFunction.intRangeValidate(0, 10, helperFunction.intNumberValidate(scanner.nextLine()));

                todoController.setCompleted(id);
                todoController.printAllTodoByCategoryId();
                System.out.println();
                System.out.println("======================");

                System.out.println();
            } else if (choice == 0) {
                condition = false;


            }

            database.closeConnection();

        }
    }

}
