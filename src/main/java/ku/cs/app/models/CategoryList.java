package ku.cs.app.models;

import java.util.ArrayList;

public class CategoryList {
    //-------------------------------------------- instance

    private ArrayList<Category> catList;
    public CategoryList(){ catList = new ArrayList<>();}
    public void addCategory(Category category){catList.add(category);}
    public ArrayList<Category> getCategory(){return catList;}
}
