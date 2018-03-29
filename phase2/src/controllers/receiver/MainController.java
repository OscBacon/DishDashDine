package controllers.receiver;

import controllers.Logging;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    private Button addToInventorybtn;

    @FXML
    private TextField ingredientName;

    @FXML
    private TextField quantityOfIngredient;

    @FXML
    void addIngredientToInventory(ActionEvent event) {
        String ingredient = ingredientName.getText();
        String quantity = quantityOfIngredient.getText();
        Logging.addToInventory(ingredient, quantity);
    }

}
