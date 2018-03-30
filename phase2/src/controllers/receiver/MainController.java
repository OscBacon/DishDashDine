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

    /**
     * Initializes the view and its contents.
     */
    @FXML
    void initialize() {
        Restaurant.setAlertedController(this);
    }

    /**
     * Keeps track of the inventory so that it could display it in this view.
     * @param event not used
     */
    @FXML
    void addIngredientToInventory(ActionEvent event) {
        String ingredient = ingredientName.getText();
        String quantity = quantityOfIngredient.getText();
        Logging.addToInventory(ingredient, quantity);
    }

}
