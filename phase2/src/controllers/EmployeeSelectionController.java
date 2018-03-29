package controllers;

import controllers.waiter.MainController;
import controllers.waiter.SelectName;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    /**
     * Dialog page creation adapted from http://code.makery.ch/library/javafx-8-tutorial/part3/ on March 28th, 2018, 10:40 PM.
     * <p>
     */
    public void openEmployeeView(ActionEvent actionEvent) throws IOException{
        String selectedEmployeeType = employeeTypeList.getSelectionModel().getSelectedItem();;
        switch (selectedEmployeeType) {
            case "Manager":
                Parent managerPage = FXMLLoader.load(Restaurant.class.getResource("../resources/views/Manager.fxml"));
                Restaurant.stage.getScene().setRoot(managerPage);
                Restaurant.stage.show();
                break;
            case "Waiter":
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Restaurant.class.getResource("../resources/views/WaiterSelectName.fxml"));
                AnchorPane dialogPage = loader.load();
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Waiter Name Selection");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(Restaurant.stage);
                Scene scene = new Scene(dialogPage);
                dialogStage.setScene(scene);
                SelectName dialogController = loader.getController();
                dialogController.setDialogStage(dialogStage);

                dialogStage.showAndWait();

                FXMLLoader waiterLoader = new FXMLLoader();
                waiterLoader.setLocation(Restaurant.class.getResource("../resources/views/Waiter.fxml"));
                Parent waiterPage = waiterLoader.load();
                MainController waiterController = waiterLoader.getController();
                waiterController.setName(dialogController.getName());
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

