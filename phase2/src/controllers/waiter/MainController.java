package controllers.waiter;

import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Window;
import models.Waiter;
import javafx.stage.Stage;
import controllers.waiter.MainController;
import controllers.waiter.SelectName;
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

public class MainController {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @FXML
    private Button selectTableBtn;

    @FXML
    private ListView<String> displayTablesList = new ListView<String>();

    @FXML
    private Button createNewOrderBtn;


    @FXML
    public void initialize() {
        Waiter currWaiter = (Waiter) Restaurant.getListenerList().get("Waiter " + name);
        ObservableList currentBills = FXCollections.observableArrayList(currWaiter.getBillList());
        displayTablesList.setItems(currentBills);
    }
    @FXML
    void createNewOrderForTable(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Restaurant.class.getResource("../resources/views/WaiterNewTableOrder.fxml"));
        AnchorPane orderPage = loader.load();
        Stage orderStage = new Stage();
        orderStage.setTitle("Table Order Creation");
        orderStage.initModality(Modality.WINDOW_MODAL);
        orderStage.initOwner(Restaurant.stage);
        Scene scene = new Scene(orderPage);
        orderStage.setScene(scene);
        NewTableOrder orderController = loader.getController();
        orderController.setDialogStage(orderStage);
        orderController.setName(name);
        orderStage.showAndWait();
    }

    @FXML
    void waiterSelectsTable(ActionEvent event) {

    }
}
