package ku.cs.app.models.list;

import javafx.animation.ScaleTransition;

import java.util.ArrayList;
import java.util.Collections;

public class DynamicCategory implements CheckIfExistAndReturnObject<String>{
    private ArrayList<String> list;
    public DynamicCategory() {
        list = new ArrayList<>();
    }
    public void addCategory(String s) {
        list.add(s);
    }
    public void removeCategory(String s) {
        list.remove(s);
    }
    public ArrayList<String> getAllCategory() {
        Collections.sort(list);
        return list;
    }

    @Override
    public boolean checkIfExist(String s) {
        for (String category: list) {
            if (category.equals(s)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public String returnObject(String s) {
        for (String category: list) {
            if (category.equals(s)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "DynamicCategory{" +
                "list=" + list +
                '}';
    }
}
