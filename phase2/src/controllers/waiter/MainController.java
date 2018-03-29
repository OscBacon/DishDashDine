package controllers.waiter;

import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Waiter;


import javax.xml.ws.FaultAction;

public class MainController {

    @FXML
    private Button selectTableBtn;

    @FXML
    private ListView<String> displayTablesList = new ListView<String>();

    @FXML
    private Button createNewOrderBtn;


    @FXML
    public void initialize() {
        Waiter currWaiter = Restaurant.getListenerList().get("Waiter " + name);
        ObservableList currentBills = FXCollections.observableArrayList(currWaiter.getBillList());
        displayTablesList.setItems(currentBills);
    }
    @FXML
    void createNewOrderForTable(ActionEvent event) {

    }

    @FXML
    void waiterSelectsTable(ActionEvent event) {

    }

}
