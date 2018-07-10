package test;

import controller.CategoryController;
import db.Database;

public class TestApp {

    public static void main(String[] args) {

        Database database = new Database("mytodo.db");

        CategoryController categoryController = new CategoryController(database);

        System.out.println(categoryController.getCategoryByID(5));

    }
}
