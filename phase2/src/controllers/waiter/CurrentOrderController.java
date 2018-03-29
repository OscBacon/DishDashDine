package controllers.waiter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import models.Bill;
import models.Dish;
import models.Waiter;

import javax.swing.text.html.ListView;


public class CurrentOrderController {

        private Waiter currWaiter;
        private int tableNumber;

        @FXML
        private ListView currentBill;

        @FXML
        private Button RemoveDishbtn;

        @FXML
        void initialize() {
            Bill bill = currWaiter.getActiveBill(tableNumber);
            ObservableList <Dish> dish = FXCollections.observableArrayList(bill.getDishList().values());

        }

        @FXML
        void RemoveCurrentDishFromBill(ActionEvent event) {

    }
}
