package controllers.manager;

import controllers.Restaurant;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.InventoryItem;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewInventoryController {
    public TableView<String> inventoryTable = new TableView<>();
    public TableColumn<String, String> ingredientColumn;
    public TableColumn<String, Integer> quantityColumn;
    ObservableMap<String, Integer> observableMap = FXCollections.observableHashMap();
    ObservableList<String> observableArray = FXCollections.observableArrayList();
    private Stage dialogStage;
    private HashMap<String, Integer> inventoryQuantityHashmap = new HashMap<>();

    /**
     * Initializes the view and its contents.
     */
    public void initialize() {
        HashMap<String, InventoryItem> inventory = Restaurant.getInventory();

        ArrayList<String> itemArrayList = new ArrayList<>(inventory.keySet());

        inventoryTable.setItems(FXCollections.observableList(itemArrayList));

        ingredientColumn.setCellValueFactory(cd -> new SimpleStringProperty(cd.getValue()));
        quantityColumn.setCellValueFactory(cd -> new SimpleIntegerProperty(inventory.get(cd.getValue())
                .getQuantity()).asObject());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
