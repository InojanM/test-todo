package model;

public class TodoItem {

    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String CATEGORY_ID = "category_id";
    public static final String COMPLETED = "completed";

    private int id;
    private int completed;
    private int categoryID;
    private String title;

    private Category category;

    public TodoItem(int id, String title, int categoryID, int completed) {
        setId(id);
        setTitle(title);
        setCategoryID(categoryID);
        setCompleted(completed);
    }

    public TodoItem(String title, int categoryID, int completed) {
        setTitle(title);
        setCategoryID(categoryID);
        setCompleted(completed);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * ToString()
     * @return a string rep of todoItem
     */
    @Override
    public String toString() {
        return String.format("%s (%s)", getTitle(), getCategory());
    }

}
