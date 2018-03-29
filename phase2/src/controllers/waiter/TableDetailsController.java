package controllers.waiter;
import controllers.Logging;
import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import models.Bill;
import models.Dish;
import models.MenuItem;
import models.Waiter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TableDetailsController {
    private Bill bill;

    private Stage dialogStage;

    Bill currentBill;
    String tableNumber;

    @FXML
    private ListView<String> menuList;

    @FXML
    private ListView<String> dishAddition;

    @FXML
    private ListView<String> dishSubtraction;

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

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void addDishToBill(ActionEvent event) {
        String dishName = menuList.getSelectionModel().getSelectedItem();
        if (dishName != null) {
            if ((dishAddition.getSelectionModel().getSelectedItems() == null) && (dishSubtraction.getSelectionModel().getSelectedItems() == null)) {
                Logging.orderDish(currentBill.getWaiter().getName(), dishName, tableNumber);
            } else if ((dishAddition.getItems() != null) || (dishSubtraction.getItems() != null)){
                ArrayList dishadd = (ArrayList) dishAddition.getSelectionModel().getSelectedItems();
                ArrayList dishsubtract = (ArrayList) dishSubtraction.getSelectionModel().getSelectedItems();
                Logging.orderDish(currentBill.getWaiter().getName(), dishName, dishadd.toString(), dishsubtract.toString(),tableNumber);
            }
        }
    }

        @FXML
        void showAllowedAdditions (ActionEvent event){
            dishAddition.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            String dishName = menuList.getSelectionModel().getSelectedItem();
            MenuItem dishItem = Restaurant.getMenu().get(dishName);
            ObservableList allowedAdditions = FXCollections.observableArrayList(dishItem.getAllowedAdditions());
            dishAddition.setItems(allowedAdditions);
        }

        @FXML
        void showAllowedSubtraction (ActionEvent event){
            dishSubtraction.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            String dishName = menuList.getSelectionModel().getSelectedItem();
            MenuItem dishItem = Restaurant.getMenu().get(dishName);
            ObservableList allowedSubtractions = FXCollections.observableArrayList(dishItem.getAllowedSubtractions());
            dishSubtraction.setItems(allowedSubtractions);
        }

        @FXML
        void showCurrentBill (ActionEvent event){

        }

}

