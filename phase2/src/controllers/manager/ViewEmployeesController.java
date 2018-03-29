package controllers.manager;

import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ViewEmployeesController {
    public ListView<String> WaitersList;
    public ListView<String> CooksList;

    @FXML
    public void initialize() {
        WaitersList.setItems(FXCollections.observableArrayList(Restaurant.getWaiterNameList()));
        CooksList.setItems(FXCollections.observableArrayList(Restaurant.getCookNameList()));
    }

}
