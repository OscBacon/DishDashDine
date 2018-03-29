package controllers.manager;

import controllers.Logging;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;


public class CreateEmployeeController {

    @FXML
    private TextField newEmployeeName;

    @FXML
    private MenuButton NewEmployeeTypebtn;

    @FXML
    private Button createEmployeebtn;

    @FXML
    void createEmployee(ActionEvent event) {
        String employeeName = newEmployeeName.getText();
        String selectedEmployeeType = NewEmployeeTypebtn.getTypeSelector();
        switch (selectedEmployeeType) {
            case "Waiter":
                Logging.addWaiter(employeeName);
                System.out.println("You created a Waiter called " +employeeName);
                break;
            case "Cook":
                Logging.addCook(employeeName);
                System.out.println("You created a Cook called " +employeeName);
                break;
        }
    }


}
