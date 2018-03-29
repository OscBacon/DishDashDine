package controllers.waiter;
import controllers.Logging;
import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import models.Bill;
import models.Dish;
import models.Waiter;

import javax.swing.text.html.ListView;


public class WaiterCurrentOrder {

        private Waiter currWaiter;

        @FXML
        private ListView currentBill;

        @FXML
        private Button RemoveDishbtn;

        @FXML
        void initialize() {

        }

        @FXML
        void RemoveCurrentDishFromBill(ActionEvent event) {
            Logging.removeDish(currWaiter.getName(), );

    }
}
