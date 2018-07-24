package model;

public class Category {

    public static final String ID = "id";
    public static final String CATEGORY_NAME = "category_name";

    private int id;
    private String categoryName;

    public Category(int id, String categoryName) {
        setId(id);
        setCategoryName(categoryName);
    }

    public Category(String categoryName) {
        setCategoryName(categoryName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
      return getId()+". "+getCategoryName();

    }
}
