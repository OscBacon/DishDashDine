package controllers.waiter;

import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Bill;
import models.Waiter;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainController {
    private String name;
    private Waiter currWaiter;
    private HashMap<String, Bill> currentBills;
    private ArrayList<String> currentTables;

    @FXML
    private Button selectTableBtn;

    @FXML
    private ListView<String> tablesList = new ListView<>();

    @FXML
    private Button createNewOrderBtn;


    @FXML
    public void initialize() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println(this.name);
        currWaiter = (Waiter) Restaurant.getListenerList().get("Waiter " + name);
    }

    public void createList() {
        currentBills = (HashMap<String,Bill>) currWaiter.getFormattedBillList();
        currentTables = new ArrayList<>(currentBills.keySet());
        tablesList.setItems(FXCollections.observableArrayList(currentTables));
    }

    /**
     * Page creation adapted from http://code.makery.ch/library/javafx-8-tutorial/part3/ on March 28th, 2018, 10:40 PM.
     * <p>
     */
    @FXML
    void createNewOrder(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Restaurant.class.getResource("../resources/views/WaiterNewTableOrder.fxml"));
        AnchorPane orderPage = loader.load();
        Stage orderStage = new Stage();
        orderStage.setTitle("Table Order Creation");
        orderStage.initModality(Modality.WINDOW_MODAL);
        orderStage.initOwner(Restaurant.stage);
        Scene scene = new Scene(orderPage);
        orderStage.setScene(scene);
        NewTableOrderController orderController = loader.getController();
        orderController.setDialogStage(orderStage);
        orderController.setName(name);
        orderStage.showAndWait();
        createList();

    }

    /**
     * Page creation adapted from http://code.makery.ch/library/javafx-8-tutorial/part3/ on March 28th, 2018, 10:40 PM.
     * <p>
     */
    @FXML
    void selectTable(ActionEvent event) throws IOException{
        String table = tablesList.getSelectionModel().getSelectedItem();
        if (table != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Restaurant.class.getResource("../resources/views/WaiterTableDetails.fxml"));
            AnchorPane detailsPage = loader.load();
            Stage detailsStage = new Stage();
            detailsStage.setTitle("Table Details");
            detailsStage.initModality(Modality.WINDOW_MODAL);
            detailsStage.initOwner(Restaurant.stage);
            Scene scene = new Scene(detailsPage);
            detailsStage.setScene(scene);
            TableDetailsController detailsController = loader.getController();
            detailsController.setDialogStage(detailsStage);
            detailsController.setBill(currentBills.get(table));
            detailsStage.showAndWait();
        }
    }
}
