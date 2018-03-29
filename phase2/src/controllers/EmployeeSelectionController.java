package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class EmployeeSelectionController {
    @FXML
    public Button loginButton;

    @FXML
    private ListView<String> employeeTypeList = new ListView<String>();

    @FXML
    public void initialize() {
        ObservableList<String> items = FXCollections.observableArrayList("Manager", "Waiter", "Kitchen", "Receiver");
        employeeTypeList.setItems(items);
    }
}
