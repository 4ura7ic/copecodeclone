package ku.cs.app.models;

import java.util.ArrayList;

public class Category {
    //-------------------------------------------- instance

    private ArrayList<String> category;

    //-------------------------------------------- constructor

    public Category() {
        category = new ArrayList<>();
    }
    public ArrayList<String> getCategory() {
        return category;
    }
    public void addCategoryName(String categoryName) {
        this.category.add(categoryName);
    }

}
