package controllers.kitchen;

import controllers.Alerted;
import controllers.Logging;
import controllers.Restaurant;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private TableView<Dish> acceptedDishesTable = new TableView<>();
    @FXML
    private TableColumn<Dish, Integer> dishIdColumn;
    @FXML
    private TableColumn<Dish, String> dishNameColumn;
    @FXML
    private TableColumn<Dish, String> cookColumn;
    @FXML
    private ComboBox<String> cooksComboBox = new ComboBox<>();

    ObservableList<Dish> dishList;

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
        dishList = FXCollections.observableArrayList(Kitchen.getDishList().values());
        acceptedDishesTable.setItems(dishList);
    }

    @FXML
    private void createNextDishLabel() {
        if (!Kitchen.hasPendingDishes()) {
            nextDishLabel.setText("No dish pending.");
        } else {

            nextDishLabel.setText(Kitchen.getFirstDish().toString());
        }
    }

    @FXML
    void acceptCurrentDish() {
        String acceptedDishCookName = cooksComboBox.getSelectionModel().getSelectedItem();
        if (Kitchen.hasPendingDishes() && acceptedDishCookName != null) {
            dishList.add(Kitchen.getFirstDish());
            Logging.acceptDish(acceptedDishCookName);
            createNextDishLabel();
            acceptedDishesTable.refresh();
        }
    }

    /**
     * This function is called when a cook clicks on a dish that is ready for pick-up.
     */
    public void readyDish() {
        Dish selectedDish = acceptedDishesTable.getSelectionModel().getSelectedItem();
        if (selectedDish != null) {
            Logging.finishDish(String.valueOf(selectedDish.getDishId()));
            dishList.remove(selectedDish);
            acceptedDishesTable.refresh();
        }
    }

    /**
     * This method updates the next pending dish.
     */
    public void setPendingDishes() {
        createNextDishLabel();
    }
}