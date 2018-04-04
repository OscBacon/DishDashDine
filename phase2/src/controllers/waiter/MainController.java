package controllers.waiter;

import controllers.Alerted;
import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Bill;
import models.Waiter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainController extends Alerted {
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

    /**
     * Initializes the view and its contents.
     */
    @FXML
    public void initialize() {
        Restaurant.setAlertedController(this);
    }

    /**
     * Returns the name of the waiter who's associated with this view.
     * @return String the name of the waiter.
     */
    public String getName() {
        return name;
    }


    /**
     * Sets the name of this.waiter to be the parameter name.
     * @param name String; the name of the waiter.
     */
    public void setName(String name) {
        this.name = name;
        currWaiter = (Waiter) Restaurant.getListenerList().get("Waiter " + name);
    }

    /**
     * Creates a list of this waiter's current, active bills.
     */
    public void createList() {
        currentBills = (HashMap<String, Bill>) currWaiter.getFormattedBillList();
        currentTables = new ArrayList<>(currentBills.keySet());
        tablesList.setItems(FXCollections.observableArrayList(currentTables));
    }

    /**
     * Page creation adapted from http://code.makery.ch/library/javafx-8-tutorial/part3/ on March 28th, 2018, 10:40 PM.
     * <p>
     */
    @FXML
    void createNewOrder() throws IOException {
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
        currentTables.add("Table " + String.valueOf(orderController.getTableNumberInput()));
        createList();
        tablesList.refresh();
    }

    /**
     * Page creation adapted from http://code.makery.ch/library/javafx-8-tutorial/part3/ on March 28th, 2018, 10:40 PM.
     * <p>
     */
    @FXML
    void selectTable() throws IOException {
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
            detailsController.setBill(currentBills.get(table));
            detailsController.setDialogStage(detailsStage);
            detailsController.createActiveDishesList();
            detailsStage.showAndWait();
            createList();
        }
    }
}
