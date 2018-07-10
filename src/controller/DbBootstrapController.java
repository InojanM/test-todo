package controller;

import db.Database;

public class DbBootstrapController {

    private Database db;


    public DbBootstrapController(Database db) {
        this.db = db;
    }

    public boolean initTodoDatabase() {

        boolean result = db.executeUpdate("CREATE TABLE \"categories\" (\n" +
                "  \"id\" INTEGER NOT NULL,\n" +
                "  \"category_name\" TEXT,\n" +
                "  PRIMARY KEY (\"id\")\n" +
                ");");

        if (result) {
            db.executeUpdate("CREATE TABLE \"todo_items\" (\n" +
                    "  \"id\" INTEGER NOT NULL,\n" +
                    "  \"title\" TEXT,\n" +
                    "  \"category_id\" INTEGER,\n" +
                    "  \"completed\" integer,\n" +
                    "  PRIMARY KEY (\"id\"),\n" +
                    "  CONSTRAINT \"cat_fk\" FOREIGN KEY (\"category_id\") REFERENCES \"categories\" (\"id\")\n" +
                    ");");

            return true;
        }

        return false;
    }

}
