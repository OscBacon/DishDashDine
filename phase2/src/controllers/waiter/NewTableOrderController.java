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

    /**
     * Sets this view's dialogStage.
     * @param dialogStage the value to be stored in this.dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Sets this.name to be the name parameter.
     * @param name The String to be assigned to this.name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates a bill to keep track the bill of the table whose tableNum is name.
     * @param actionEvent not used
     */
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

    /**
     * Returns the table number inputted into the tableNum textField.
     * @return a TextField containing the value.
     */
    public TextField getTableNumberInput() {
        return TableNumberInput;
    }

    /**
     * Splits the bill if the checkbox is selected.
     */
    public void splitCurrentBill() {
        Logging.splitBill(boolSplitBill.isSelected(), TableNumberInput.getText());
    }
}

