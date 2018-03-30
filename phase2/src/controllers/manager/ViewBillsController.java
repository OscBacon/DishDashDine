package controllers.manager;

import controllers.Logging;
import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ViewBillsController {

    @FXML
    private ListView<?> todayBills;

    public void initialize() {
        ObservableList todaysPaidBills = FXCollections.observableArrayList(Restaurant.getPaidBills());
        todayBills.setItems(todaysPaidBills);
    }

}
