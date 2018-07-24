package db;

import java.io.File;
import java.sql.*;

public class Database {

    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;


    public Database(String dbFile) {

        connection = null;

        try {

            this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbFile);
            this.statement = connection.createStatement();

        } catch (SQLException ex) {
            System.out.println("Error: connection failed.");
            System.out.println(ex.getMessage());
        }

    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Execute sql update statements such as INSERT, DELETE, UPDATE
     *
     * @param sql query
     */
    public boolean executeUpdate(String sql) {

        try {
            this.statement.executeUpdate(sql);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return false;
    }

    /**
     * Executes query and return a ResultSet
     *
     * @param sql query
     * @return ResultSet or null
     */
    public ResultSet executeQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    /**
     * Checks if database file exists.
     *
     * @param dbFile name of the file.
     * @return true or false.
     */
    public static boolean fileExists(String dbFile) {
        File f = new File(dbFile);
        return f.exists();

    }

    /**
     * Return a prepared statement if exists, otherwise create a new prepared statement
     * and return it.
     *
     * @param sql sql statement
     * @return prepared statement
     */
    public PreparedStatement getPreparedStatement(String sql) {

        if (preparedStatement == null) {
            try {
                preparedStatement = connection.prepareStatement(sql);

                return preparedStatement;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            return preparedStatement;
        }

        return null;
    }

    /**
     * Create and return a new prepared statement.
     *
     * @param sql a statement to create
     * @return if created return a new prepared statement
     */
    public PreparedStatement createPreparedStatement(String sql) {
        try {
            preparedStatement = connection.prepareStatement(sql);
            return preparedStatement;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

}
