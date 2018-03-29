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
        //Switching scenes. Create new Table

    }

    @FXML
    void waiterSelectsTable(ActionEvent event) {

    }
}
