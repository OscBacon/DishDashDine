package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;

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

    public void openEmployeeView(ActionEvent actionEvent) throws IOException{
        String selectedEmployeeType = employeeTypeList.getSelectionModel().getSelectedItem();;
        switch (selectedEmployeeType) {
            case "Manager":
                Parent managerPage = FXMLLoader.load(Restaurant.class.getResource("../resources/views/Manager.fxml"));
                Restaurant.stage.getScene().setRoot(managerPage);
                Restaurant.stage.show();
                break;
            case "Waiter":
                Parent waiterPage = FXMLLoader.load(Restaurant.class.getResource("../resources/views/SelectWaiter.fxml"));
                Restaurant.stage.getScene().setRoot(waiterPage);
                Restaurant.stage.show();
                break;
            case "Kitchen":
                Parent kitchenPage = FXMLLoader.load(Restaurant.class.getResource("../resources/views/Kitchen.fxml"));
                Restaurant.stage.getScene().setRoot(kitchenPage);
                Restaurant.stage.show();
                break;
            case "Receiver":
                Parent receiverPage = FXMLLoader.load(Restaurant.class.getResource("../resources/views/Receiver.fxml"));
                Restaurant.stage.getScene().setRoot(receiverPage);
                Restaurant.stage.show();
                break;

        }
    }
}

