package controllers.waiter;

import controllers.Restaurant;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SelectName {
    public ListView<String> waiterNamesListView = new ListView<String>();
    public Button selectWaiterBtn;
    private String name;
    private Stage dialogStage;

    @FXML
    public void initialize() {
        ObservableList<String> waiterNames = FXCollections.observableArrayList(Restaurant.getWaiterNameList());
        waiterNamesListView.setItems(waiterNames);
    }

    @FXML
    public void selectWaiterName(ActionEvent actionEvent) {
        name = waiterNamesListView.getSelectionModel().getSelectedItem();
        System.out.println(name);
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
