package controllers;

import javafx.scene.control.Alert;

public abstract class Alerted {
    /**
     * Creates a pop-up on the screen of the controller that implements this interface.
     *
     * @param message The String that will show up in the pop-up.
     */
    public void createAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("DishDashDine!");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
