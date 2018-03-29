package controllers.waiter;

import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Dish;

import java.util.ArrayList;

public class TableDetailsController {

    @FXML
    private ListView<String> menuList;

    @FXML
    private Button dishAddition;

    @FXML
    private Button dishSubtraction;

    @FXML
    private Button dish;

    @FXML
    private Button currentOrder;

    @FXML
    private ListView<Dish> activeDishesToBeDelivered;

    public void initialize() {
        ArrayList<String> menuItemName = new ArrayList<String>(Restaurant.getMenu().keySet());
        ObservableList<String> menuItem = FXCollections.observableArrayList(menuItemName);
        menuList.setItems(menuItem);

        
    }

    @FXML
    void addDishToBill(ActionEvent event) {

    }

    @FXML
    void showAllowedAdditions(ActionEvent event) {

    }

    @FXML
    void showAllowedSubtraction(ActionEvent event) {

    }

    @FXML
    void showCurrentBill(ActionEvent event) {

    }

}
