package controllers.manager;

import controllers.Logging;
import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ViewUndeliveredDishesController {

    @FXML
    private ListView<?> unDeliveredDishes;

    public void initialize() {
        ObservableList allUndeliveredDishes = FXCollections.observableArrayList(Restaurant.getUndeliveredDishes().values());
        unDeliveredDishes.setItems(allUndeliveredDishes);

    }

}
