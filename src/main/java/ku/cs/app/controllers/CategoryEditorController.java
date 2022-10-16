package ku.cs.app.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import ku.cs.app.models.User;
import com.github.saacsos.FXRouter;
import ku.cs.app.models.list.DynamicCategory;
import ku.cs.app.services.DynamicCategoryFileSource;

import java.io.IOException;


public class CategoryEditorController {
    @FXML private ListView categoryListView;
    @FXML private Label errorMsgLabel;
    @FXML private TextField newCategoryTextField;

    private DynamicCategoryFileSource dynamicCategoryFileSource;
    private DynamicCategory dynamicCategory;

    private User user;
    private String selectedCategory;

    public void initialize() {
        user = (User) FXRouter.getData();
        dynamicCategoryFileSource = new DynamicCategoryFileSource("data", "category.csv");
        dynamicCategory = dynamicCategoryFileSource.readData();
        showListView();
        handleSelectedListView();
        errorMsgLabel.setText("");
    }

    @FXML
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("main_admin_form", user);
        } catch (IOException e) {
            System.err.println("err ไป How to ไม่ได้");
            System.err.println("ให้ตรวจสอบการกําหนด route");
            e.printStackTrace();
        }
    }
    @FXML
    public void handleRemoveButton(ActionEvent actionEvent) {
        errorMsgLabel.setText("");
        if (selectedCategory == null) {
            errorMsgLabel.setText("Please select the category to remove.");
        } else {
            dynamicCategory.removeCategory(selectedCategory);
            showListView();
            dynamicCategoryFileSource.writeData(dynamicCategory);
            errorMsgLabel.setText("Category removed.");
        }
    }
    @FXML
    public void handleAddButton(ActionEvent actionEvent) {
        errorMsgLabel.setText("");
        if (newCategoryTextField.getText().equals("")) {
            errorMsgLabel.setText("Please enter the category to add.");
        }
        else if(dynamicCategory.checkIfExist(newCategoryTextField.getText())) {
            errorMsgLabel.setText("This category is already existed.");
        } else {
            dynamicCategory.addCategory(newCategoryTextField.getText());
            showListView();
            dynamicCategoryFileSource.writeData(dynamicCategory);
            errorMsgLabel.setText("Category Added.");
        }
    }

    @FXML
    public void handleSelectedListView() {
        categoryListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<String>() {
                    @Override
                    public void changed(ObservableValue observableValue, String oldString, String newString) {
                        selectedCategory = newString;
                        System.out.println(selectedCategory + " is selected");
                    }
                }
        );
    }

    private void showListView() {
        categoryListView.getItems().clear();
        categoryListView.getItems().addAll(dynamicCategory.getAllCategory());
        categoryListView.refresh();

    }
}
