package controller;

import db.Database;
import model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryController {


    private Database database;

    public CategoryController(Database database) {
        this.database = database;
    }

    /**
     * Select all categories from categories table.
     *
     * @return ArrayList of Categories or null
     */
    public ArrayList<Category> getAllCategories() {

        ArrayList<Category> categories = new ArrayList<>();

        ResultSet resultSet = database.executeQuery("SELECT * FROM categories");

        try {

            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt(Category.ID),
                        resultSet.getString(Category.CATEGORY_NAME)
                );

                categories.add(category);
            }

            return categories;

        } catch (SQLException ex) {

        }

        return null;
    }

    /**
     * Get a category by it's id
     * @param id category id
     * @return Category or null
     */
    public Category getCategoryByID(int id) {

        try {

            Category category = null;

            PreparedStatement preparedStatement = database.createPreparedStatement("SELECT * FROM categories WHERE id=? LIMIT 1");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                category = new Category(resultSet.getInt(Category.ID), resultSet.getString(Category.CATEGORY_NAME));
            }

            return category;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
