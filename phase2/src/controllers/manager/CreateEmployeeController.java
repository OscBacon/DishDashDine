package controllers.manager;

import controllers.Logging;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;


public class CreateEmployeeController {
    public ListView<String> employeeTypeView = new ListView<>();
    private Stage dialogStage;
    @FXML
    private TextField newEmployeeName;

    @FXML
    public void initialize() {
        ArrayList<String> employeeTypeList = new ArrayList<String>();
        employeeTypeList.add("Waiter");
        employeeTypeList.add("Cook");
        employeeTypeView.setItems(FXCollections.observableArrayList(employeeTypeList));
    }

    @FXML
    void createEmployee() {
        String employeeName = newEmployeeName.getText();
        String selectedEmployeeType = employeeTypeView.getFocusModel().getFocusedItem();
        System.out.println(selectedEmployeeType);
        if (employeeName != null && selectedEmployeeType != null) {
            switch (selectedEmployeeType) {
                case "Waiter":
                    Logging.addWaiter(employeeName);
                    System.out.println("You created a Waiter called " + employeeName);
                    break;
                case "Cook":
                    Logging.addCook(employeeName);
                    System.out.println("You created a Cook called " + employeeName);
                    break;
            }
            dialogStage.close();
        }
    }

    /**
     * Sets this controller's dialogStage
     * @param stage the Stage dialogStage
     */
    public void setDialogStage(Stage stage) {
        dialogStage = stage;
    }
}
