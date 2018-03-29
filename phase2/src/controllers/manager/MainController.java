package controllers.manager;

import controllers.Alerted;
import controllers.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController extends Alerted {
    @FXML
    public void initialize() {
        Restaurant.setAlertedController(this);
    }

    public void createNewEmployee(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Restaurant.class.getResource("../resources/views/ManagerCreateEmployee.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        stage.setTitle("New Employee");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Restaurant.stage);
        Scene scene = new Scene(page);
        stage.setScene(scene);
        CreateEmployeeController controller = loader.getController();
        controller.setDialogStage(stage);
        stage.showAndWait();
    }

    public void viewInventory(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Restaurant.class.getResource("../resources/views/ManagerViewInventory.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Inventory");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Restaurant.stage);
        Scene scene = new Scene(page);
        stage.setScene(scene);
        ViewInventoryController controller = loader.getController();
        controller.setDialogStage(stage);
        stage.showAndWait();
    }

    public void viewAllEmployees(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Restaurant.class.getResource("../resources/views/ManagerViewEmployees.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Employees");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Restaurant.stage);
        Scene scene = new Scene(page);
        stage.setScene(scene);
        ViewEmployeesController controller = loader.getController();
        controller.setDialogStage(stage);
        stage.showAndWait();
    }

    public void viewUndeliveredDishes(ActionEvent actionEvent) {
    }

    public void viewAllBills(ActionEvent actionEvent) {
    }
}
