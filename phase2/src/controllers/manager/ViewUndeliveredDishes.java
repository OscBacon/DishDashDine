package controllers.manager;

import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ViewUndeliveredDishes {

    @FXML
    private ListView<?> unDeliveredDishes;

    public void initializer() {
        ObservableList allUndeliveredDishes = FXCollections.observableArrayList(Restaurant.getUndeliveredDishes().values());
        unDeliveredDishes.setItems(allUndeliveredDishes);
    }

}
