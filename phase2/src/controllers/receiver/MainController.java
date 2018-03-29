package controllers.receiver;

import controllers.Alerted;
import controllers.Logging;
import controllers.Restaurant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController extends Alerted {

    @FXML
    private Button addToInventorybtn;

    @FXML
    private TextField ingredientName;

    @FXML
    private TextField quantityOfIngredient;

    @FXML
    void initialize() {
        Restaurant.setAlertedController(this);
    }

    @FXML
    void addIngredientToInventory(ActionEvent event) {
        String ingredient = ingredientName.getText();
        String quantity = quantityOfIngredient.getText();
        Logging.addToInventory(ingredient, quantity);
    }

}
