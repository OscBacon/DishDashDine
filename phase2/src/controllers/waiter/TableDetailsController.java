package controllers.waiter;
import controllers.Logging;
import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Bill;
import models.Dish;
import models.MenuItem;
import models.Waiter;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class TableDetailsController {
    private Bill bill;

    private Stage dialogStage;

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
        tableNumber = String.valueOf(this.bill.getTableNumber());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void addDishToBill(ActionEvent event) {
        String dishName = menuList.getSelectionModel().getSelectedItem();
        if (dishName != null) {
            ArrayList<String> additions = new ArrayList<>(dishAddition.getSelectionModel().getSelectedItems());
            ArrayList<String> subtractions = new ArrayList<>(dishSubtraction.getSelectionModel().getSelectedItems());
            if (additions.isEmpty() && subtractions.isEmpty()) {
                Logging.orderDish(bill.getWaiter().getName(), dishName, tableNumber);
            }
            else {
                String additionsJoined = String.join(", ", additions);
                String subtractionsJoined = String.join(", ", subtractions);
                Logging.orderDish(bill.getWaiter().getName(), dishName, additionsJoined, subtractionsJoined, tableNumber);
            }
        }
    }

    void createActiveDishesList() {
        ArrayList<Dish> dishList = new ArrayList<>(bill.getDishList().values());
        ArrayList<Dish> activeDishList = new ArrayList<>();
        for (Dish dish: dishList) {
            if (!dish.getDelivered()) {
                activeDishList.add(dish);
            }
        }

        activeDishesToBeDelivered.setItems(FXCollections.observableArrayList(activeDishList));
    }

        @FXML
        void showAllowedAdditions (){
            dishAddition.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            String dishName = menuList.getSelectionModel().getSelectedItem();
            MenuItem dishItem = Restaurant.getMenu().get(dishName);
            ObservableList allowedAdditions = FXCollections.observableArrayList(dishItem.getAllowedAdditions());
            dishAddition.setItems(allowedAdditions);
        }

        @FXML
        void showAllowedSubtraction (){
            dishSubtraction.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            String dishName = menuList.getSelectionModel().getSelectedItem();
            MenuItem dishItem = Restaurant.getMenu().get(dishName);
            ObservableList allowedSubtractions = FXCollections.observableArrayList(dishItem.getAllowedSubtractions());
            dishSubtraction.setItems(allowedSubtractions);
        }

        public void showAllowedSubstitutions(MouseEvent mouseEvent) {
            showAllowedSubtraction();
            showAllowedAdditions();
        }

        @FXML
        void showCurrentBill (ActionEvent event) throws IOException {

        }


}

