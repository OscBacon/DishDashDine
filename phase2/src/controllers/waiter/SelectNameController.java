package controllers.waiter;

import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SelectNameController {
    public ListView<String> waiterNamesListView = new ListView<String>();
    public Button selectWaiterBtn;
    private String name;
    private Stage dialogStage;

    /**
     * Initializes the view and its contents.
     */
    @FXML
    public void initialize() {
        ObservableList<String> waiterNames = FXCollections.observableArrayList(Restaurant.getWaiterNameList());
        waiterNamesListView.setItems(waiterNames);
    }

    /**
     * Allows you to select a waiter name from a menu.
     */
    @FXML
    public void selectWaiterName(ActionEvent actionEvent) {
        name = waiterNamesListView.getSelectionModel().getSelectedItem();
        if (name != null) {
            dialogStage.close();
        }

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public String getName() {
        return name;
    }
}
