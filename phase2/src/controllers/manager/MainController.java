package controllers.manager;

import controllers.Alerted;
import controllers.Logging;
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

    /**
     * This method handles the manager pressing the button to create a new employee.
     */
    public void createNewEmployee() throws IOException {
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

    /**
     * This method handles the manager pressing the button to view the inventory.
     */
    public void viewInventory() throws IOException {
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

    /**
     * This method handles the manager pressing the button to create a new employee.
     */
    public void viewAllEmployees() throws IOException {
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
        stage.showAndWait();
    }

    /**
     * This method handles the manager pressing the button to view the dishes that are yet to be delivered.
     */
    public void viewUndeliveredDishes() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Restaurant.class.getResource("../resources/views/ManagerViewUndeliveredDishes.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Undelivered Dishes");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Restaurant.stage);

        Logging.showUndeliveredDishes();

        Scene scene = new Scene(page);
        stage.setScene(scene);
        ViewUndeliveredDishesController controller = loader.getController();
        stage.showAndWait();
    }

    /**
     * This method handles the manager pressing the button to see all payments done to the restaurant today.
     */
    public void viewAllBills() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Restaurant.class.getResource("../resources/views/ManagerViewBills.fxml"));
        AnchorPane page = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Bills");
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(Restaurant.stage);

        Logging.showAllPayments();

        Scene scene = new Scene(page);
        stage.setScene(scene);
        ViewBillsController controller = loader.getController();
        stage.showAndWait();
    }
}
