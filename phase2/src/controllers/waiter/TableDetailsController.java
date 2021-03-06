package controllers.waiter;

import controllers.Logging;
import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Bill;
import models.Dish;
import models.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class TableDetailsController {
    String tableNumber;
    private Bill bill;
    private Stage dialogStage;
    ArrayList<Dish> activeDishList = new ArrayList<>();
    @FXML
    private ListView<String> menuList;



    @FXML
    private TextField uniquePersonBill;

    @FXML
    private ListView<String> dishAddition;

    @FXML
    private ListView<String> dishSubtraction;

    @FXML
    private Button dish;

    @FXML
    private Button currentOrder;

    @FXML
    private Button cancelDish;

    @FXML
    private ListView<Dish> activeDishesToBeDelivered;

    public void initialize() {
        ArrayList<String> menuItemName = new ArrayList<>(Restaurant.getMenu().keySet());
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

            String name = uniquePersonBill.getText();

            if (name.equals("")) {
                name = "Undeclared";
            }

            if (additions.isEmpty() && subtractions.isEmpty()) {
                Logging.orderDish(bill.getWaiter().getName(), dishName, tableNumber, name);
            } else {
                String additionsJoined = String.join(", ", additions);
                String subtractionsJoined = String.join(", ", subtractions);
                Logging.orderDish(bill.getWaiter().getName(), dishName, additionsJoined, subtractionsJoined, tableNumber, name);
            }
        }
    }

    public void createActiveDishesList() {
        ArrayList<Dish> dishList = new ArrayList<>(bill.getWaiter().getDishList().values());
        activeDishList = new ArrayList<>();
        for (Dish dish: dishList) {
            if (String.valueOf(dish.getTableNumber()).equals(tableNumber) && !dish.getDelivered()) {
                activeDishList.add(dish);
            }
        }
        activeDishesToBeDelivered.setItems(FXCollections.observableArrayList(activeDishList));
    }

    @FXML
    void showAllowedAdditions() {
        dishAddition.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        String dishName = menuList.getSelectionModel().getSelectedItem();
        if (dishName != null) {
            MenuItem dishItem = Restaurant.getMenu().get(dishName);
            ObservableList allowedAdditions = FXCollections.observableArrayList(dishItem.getAllowedAdditions());
            dishAddition.setItems(allowedAdditions);
        }

    }

    @FXML
    void showAllowedSubtractions() {
        dishSubtraction.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        String dishName = menuList.getSelectionModel().getSelectedItem();
        if (dishName != null) {
            MenuItem dishItem = Restaurant.getMenu().get(dishName);
            ObservableList allowedSubtractions = FXCollections.observableArrayList(dishItem.getAllowedSubtractions());
            dishSubtraction.setItems(allowedSubtractions);
        }
    }

    public void showAllowedSubstitutions() {
        showAllowedSubtractions();
        showAllowedAdditions();
    }

    @FXML
    void showCurrentBill() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Restaurant.class.getResource("../resources/views/WaiterCurrentOrder.fxml"));
        AnchorPane billPage = loader.load();
        Stage billStage = new Stage();
        billStage.setTitle("Current Bill");
        billStage.initModality(Modality.WINDOW_MODAL);
        billStage.initOwner(Restaurant.stage);
        Scene scene = new Scene(billPage);
        billStage.setScene(scene);
        CurrentOrderController billController = loader.getController();
        billController.setCurrWaiter(bill.getWaiter());
        billController.setTableNumber(bill.getTableNumber());
        billController.createBill();
        billController.setDialogStage(billStage);
        billStage.showAndWait();
        dialogStage.close();
    }

    public void confirmDishDelivered(){
        Dish dishToConfirm = activeDishesToBeDelivered.getSelectionModel().getSelectedItem();
        if (dishToConfirm != null) {
            Logging.confirmDelivery(bill.getWaiter().getName(), String.valueOf(dishToConfirm.getDishId()));
            activeDishList.remove(dishToConfirm);
            activeDishesToBeDelivered.setItems(FXCollections.observableArrayList(activeDishList));
        }
    }


    public void cancelActiveDish(ActionEvent event) {
        Dish removeDish = activeDishesToBeDelivered.getSelectionModel().getSelectedItem();
        Logging.cancelDish(bill.getWaiter().getName(), String.valueOf(removeDish.getDishId()));
        activeDishList.remove(removeDish);
        activeDishesToBeDelivered.setItems(FXCollections.observableArrayList(activeDishList));
    }

}

