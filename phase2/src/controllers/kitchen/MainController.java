package controllers.kitchen;

import controllers.Alerted;
import controllers.Logging;
import controllers.Restaurant;

import javafx.beans.Observable;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Dish;
import models.Kitchen;

import java.util.ArrayList;

public class MainController extends Alerted {
    @FXML
    public Button readyDishButton;
    @FXML
    private Label nextDishLabel = new Label("No dish pending.");

    @FXML
    private Button acceptDishButton;

    @FXML
    private TableView<Dish> acceptedDishesTable;
    @FXML
    private TableColumn<Dish, Integer> dishIdColumn;
    @FXML
    private TableColumn<Dish, String> dishNameColumn;
    @FXML
    private TableColumn<Dish, String> cookColumn;
    @FXML
    private ComboBox<String> cooksComboBox = new ComboBox<>();

    @FXML
    void initialize() {
        Restaurant.setAlertedController(this);

        ArrayList<String> cookNameList = Restaurant.getCookNameList();
        cooksComboBox.setItems(FXCollections.observableArrayList(cookNameList));

        dishIdColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("dishId"));
        dishNameColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        cookColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("cook"));

        createAcceptedDishesTable();
        createNextDishLabel();

    }

    @FXML
    private void createAcceptedDishesTable() {
        ObservableList<Dish> dishList = FXCollections.observableArrayList(Kitchen.getDishList().values());
        acceptedDishesTable.setItems(dishList);
    }

    @FXML
    private void createNextDishLabel() {
        if (!Kitchen.hasPendingDishes()) {
            nextDishLabel.setText("No dish pending.");
        }
        else {
            nextDishLabel.setText(Kitchen.getFirstDish().toString());
        }
}

    @FXML
    void acceptCurrentDish(ActionEvent event) {
        String acceptedDishCookName = cooksComboBox.getSelectionModel().getSelectedItem();
        if (!nextDishLabel.getText().equals("No dish pending.") && acceptedDishCookName != null) {
            Logging.acceptDish(acceptedDishCookName);
            createAcceptedDishesTable();
            createNextDishLabel();
        }
    }

    public void readyDish(ActionEvent actionEvent) {
        Dish selectedDish = acceptedDishesTable.getSelectionModel().getSelectedItem();
        if (selectedDish != null) {
            Logging.finishDish(String.valueOf(selectedDish.getDishId()));
            createAcceptedDishesTable();
        }
    }

    public void setPendingDishes() {
        createNextDishLabel();
    }
}