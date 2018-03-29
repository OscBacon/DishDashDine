package controllers.manager;

import controllers.Restaurant;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import models.InventoryItem;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewInventoryController {
    private Stage dialogStage;

    public TableView<String> inventoryTable = new TableView<>();
    public TableColumn<String, String> ingredientColumn;
    public TableColumn<String, Integer> quantityColumn;
    private HashMap<String, Integer> inventoryQuantityHashmap = new HashMap<>();
    ObservableMap<String, Integer> observableMap = FXCollections.observableHashMap();
    ObservableList<String> observableArray = FXCollections.observableArrayList();

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
