package controllers.waiter;

import controllers.Logging;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import models.Bill;
import models.Dish;
import models.Waiter;

import java.util.ArrayList;

/**
 * Controller for WaiterCurrentOrder.fxml
 */
public class CurrentOrderController {

    /**
     * Waiter currWaiter passed into CurrentOrder Controller.
     */
    private Waiter currWaiter;

    /**
     * int tableNumber of the
     */
    private int tableNumber;
    private Bill bill;
    private ArrayList<Dish> dishList;
    private Stage dialogStage;
    @FXML
    private ListView<Dish> billView = new ListView<>();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    void initialize() {
    }

    public void setCurrWaiter(Waiter waiter) {
        this.currWaiter = waiter;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }


    public void createBill() {
        bill = currWaiter.getActiveBill(tableNumber);
        dishList = new ArrayList<>(bill.getDishList().values());
        billView.setItems(FXCollections.observableArrayList(dishList));

    }

    @FXML
    void removeCurrentDishFromBill() {
        Dish dishToRemove = billView.getSelectionModel().getSelectedItem();
        Logging.removeDish(bill.getWaiter().getName(), String.valueOf(dishToRemove.getDishId()));
        dishList.remove(dishToRemove);
        billView.setItems(FXCollections.observableArrayList(dishList));
    }

    @FXML
    void recallCurrentDish() {
        Dish dishToRecall = billView.getSelectionModel().getSelectedItem();
        Logging.dishRecall(bill.getWaiter().getName(), String.valueOf(dishToRecall.getDishId()));
        dishList.remove(dishToRecall);
        billView.setItems(FXCollections.observableArrayList(dishList));
    }

    public void payBill() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DishDashDine");
        alert.setHeaderText("Bill for Table " + tableNumber);
        alert.setContentText(bill.toString());
        alert.showAndWait();
        Logging.payBill(currWaiter.getName(), String.valueOf(tableNumber));
        dialogStage.close();
    }
}
