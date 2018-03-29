package controllers.kitchen;

import controllers.Alerted;
import controllers.Logging;
import controllers.Restaurant;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableStringValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Dish;
import models.Kitchen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Queue;

public class MainController extends Alerted {
    @FXML
    public Button readyDishButton;
    @FXML
    private ListView<String> nextDishView = new ListView<String>();
    @FXML
    private ChoiceBox<String> cooksChoiceBox;

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
    void initialize() {
        Restaurant.setAlertedController(this);
        if (Kitchen.hasPendingDishes()) {
            ArrayList<String> nextDishProperties = new ArrayList<>();
            Dish nextDish = Kitchen.getFirstDish();
            nextDishProperties.add(nextDish.getName());
            if (!nextDish.getAdditions().isEmpty()) {
                ArrayList<String> additions = nextDish.getAdditions();
                nextDishProperties.add("Additions: " + String.join(",", additions));
            }
            if (!nextDish.getSubtractions().isEmpty()) {
                ArrayList<String> subtractions = nextDish.getSubtractions();
                nextDishProperties.add("Subtractions: " + String.join(",", subtractions));
            }
            ObservableList<String> nextDishPropertiesList = FXCollections.observableArrayList(nextDishProperties);
            nextDishView.setItems(nextDishPropertiesList);
        }
        else {
            ObservableList<String> noDish = FXCollections.observableArrayList("No dish pending.");
            nextDishView.setItems(noDish);
        }

        ArrayList<String> cookNameList = Restaurant.getCookNameList();
        cooksChoiceBox = new ChoiceBox<>(FXCollections.observableArrayList(cookNameList));

        dishIdColumn.setCellValueFactory(new PropertyValueFactory<Dish, Integer>("dishId"));
        dishNameColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("name"));
        cookColumn.setCellValueFactory(new PropertyValueFactory<Dish, String>("cook"));

        ObservableList<Dish> dishList = FXCollections.observableArrayList(Kitchen.getDishList().values());
        acceptedDishesTable.setItems(dishList);
    }

    @FXML
    void acceptCurrentDish(ActionEvent event) {
        String acceptedDishCookName = cooksChoiceBox.getSelectionModel().getSelectedItem();
        Logging.acceptDish(acceptedDishCookName);
    }

    public void readyDish(ActionEvent actionEvent) {
        Dish selectedDish = acceptedDishesTable.getSelectionModel().getSelectedItem();
        if (selectedDish != null) {
            Logging.finishDish(String.valueOf(selectedDish.getDishId()));
        }
    }
}