import java.util.HashMap;

public class Restaurant {
    // A HashMap representation of the inventory
    public static HashMap inventory;

    // A HashMap representation of the menu
    public static final HashMap menu;

    public static void main(String[] args) {

    }

    /**
     * Handles the input from a new event in event.txt.
     * This sends the input to be handled to the appropriate Class or Object.
     * @param input The event input
     */
    public void handleInput(String input) {

    }

    private static void addToInventory(HashMap<String, Integer> items) {}

    private static void removeFromInventory(HashMap<String, Integer> items) {}

    /**
     * Check the inventory to see if there are enough of each items.
     * If an inventory is under its threshold for reorder, a request for reorder is added to requests.txt
     * @return False if an item is under its threshold for reorder
     */
    public boolean checkInventory() {return false;}

    /**
     * Check in the inventory whether or not there are sufficient quantities of each items.
     * @param ingredients The ingredients to be checked and the quantities needed
     * @return True if all items have sufficient quantities available
     */
    public boolean checkInventory(HashMap ingredients) {return false;}

    /**
     * Prints a string representation of the inventory.
     * @return  A string representation of the inventory
     */
    public String printInventory() {}

    /**
     * Writes a request for the given item in requests.txt
     * @param item The item to be requested
     */
    private void writeRequest(String item) {}

    /**
     * This reads through event.txt and handles input when needed
     */
    private void run() {}
}
