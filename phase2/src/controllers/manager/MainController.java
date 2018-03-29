package controllers.manager;


import com.sun.deploy.util.FXLoader;
import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MainController {

    @FXML
    private Button createEmployeebtn;

    @FXML
    private Button viewInventorybtn;

    @FXML
    private Button viewAllEmployeesbtn;

    @FXML
    void CreateNewEmployee(ActionEvent event) throws IOException {
        Parent managerHomePage = FXMLLoader.load(Restaurant.class.getResource("../resources/views/ManagerCreateEmployee.fxml"));
        Restaurant.stage.getScene().setRoot(managerHomePage);
        Restaurant.stage.show();
    }

    @FXML
    void viewAllEmployees(ActionEvent event) {

    }

    @FXML
    void viewInventory(ActionEvent event) {

    }

}
