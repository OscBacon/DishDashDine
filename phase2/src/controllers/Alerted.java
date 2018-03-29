package controllers;

public interface Alerted {
    /**
     * Creates a pop-up on the screen of the controller that implements this interface.
     *
     * @param message The String that will show up in the pop-up.
     */
    void createAlert(String message);
}
