package controllers.waiter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import models.Bill;
import models.Dish;
import models.Waiter;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;


public class CurrentOrderController {

    private Waiter currWaiter;
    private int tableNumber;
    private Bill bill;
    private ArrayList<Dish> dishList;
    private Stage dialogStage;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private ListView<Dish> billView = new ListView<>();

    @FXML
    private Button RemoveDishbtn;


    @FXML
    void initialize() {

    }

    public void setCurrWaiter(Waiter waiter) {
        this.currWaiter = waiter;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }


    void createBill(){
        bill = currWaiter.getActiveBill(tableNumber);
        dishList = new ArrayList<>(bill.getDishList().values());

        ObservableList <Dish> observableList = FXCollections.observableArrayList(dishList);
        billView.setItems(observableList);
    }

    @FXML
    void RemoveCurrentDishFromBill(ActionEvent event) {

    }
}
