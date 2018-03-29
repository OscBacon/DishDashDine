package controllers.waiter;
import controllers.Logging;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;

public class NewTableOrder {
    public CheckBox largeGroupCheckBox;
    private Stage dialogStage;
    private String name;

    @FXML
    private TextField TableNumberInput;

    @FXML
    private Button createBillButton;

    public void setDialogStage(Stage dialogStage) {
            this.dialogStage = dialogStage;
    }

    public void setName(String name) {
        this.name = name;
    }

    @FXML
    public void createBill(javafx.event.ActionEvent actionEvent) {
        if (!TableNumberInput.getText().equals("")) {
            if (largeGroupCheckBox.isSelected()) {
                Logging.newLargeBill(name, TableNumberInput.getText());
            }
            else {
                Logging.newBill(name, TableNumberInput.getText());
            }
            dialogStage.close();
        }
    }
}

