package controllers.manager;

import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class ViewEmployeesController {
    private Stage dialogStage;
    public ListView allWaitersList;
    public ListView allCooksList;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
