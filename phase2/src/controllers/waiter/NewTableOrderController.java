package controllers.waiter;

import controllers.Logging;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewTableOrderController {
    public CheckBox largeGroupCheckBox;
    private Stage dialogStage;
    private String name;

    @FXML
    private TextField TableNumberInput;

    @FXML
    private CheckBox boolSplitBill;

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
            } else {
                Logging.newBill(name, TableNumberInput.getText());
            }
            dialogStage.close();
        }
    }

    public TextField getTableNumberInput() {
        return TableNumberInput;

    public void splitCurrentBill() {
        Logging.splitBill(boolSplitBill.isSelected(), TableNumberInput.getText());

    }
}

