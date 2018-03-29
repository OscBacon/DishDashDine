package controllers.kitchen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

public class MainController {

    @FXML
    private ChoiceBox<?> AllCooks;

    @FXML
    private Label nextDishToAccept;

    @FXML
    private Button CookAcceptsDishbtn;

    @FXML
    private TableView<?> AcceptedDishesTable;

    @FXML
    void initialize() {
    
    }

    @FXML
    void cookAcceptsCurrentDish(ActionEvent event) {

    }

    @FXML
    void showAllCooks(MouseEvent event) {


    }

}