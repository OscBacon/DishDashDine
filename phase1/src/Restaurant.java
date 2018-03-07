import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurant {
    // A HashMap representation of the inventory
    private static HashMap<String, InventoryItem> inventory;

    // A HashMap representation of the menu
    private static HashMap<String, MenuItem> menu;

    private static Type menuType = new TypeToken<HashMap<String,MenuItem>>() {}.getType();
    private static Type inventoryType = new TypeToken<HashMap<String,InventoryItem>>() {}.getType();

    // An list of all objects that can listen to events
    private ArrayList<Listener> listenerList = new ArrayList<Listener>();

    public static void main(String[] args) throws IOException {
        /* Creation of individual Files is currently commented out in case they need to be created and formatted later.
        File eventsFile = new File("events.txt");
        File requestsFile = new File("requests.txt");

        File menuFile = new File("menu.json");
        File inventoryFile = new File("inventory.json");*/

        String[] fileNames = {"events.txt", "requests.txt", "menu.json", "inventory.json"};
        for (String fileName : fileNames) {
            File file = new File(fileName);
            if (!file.isFile()) {
                file.createNewFile();
            }
        }

        Gson gson = new Gson();

        menu = gson.fromJson(new FileReader("menu.json"), menuType);
        inventory = gson.fromJson(new FileReader("inventory.json"), inventoryType);
        System.out.println("Inventory: " + inventory);
        System.out.println("Menu: " + menu);

        System.out.println(printInventory());
    }

    /**
     * Handles the input from a new event in event.txt.
     * This sends the input to be handled to the appropriate Class or Object.
     * @param input The event input
     */
    public void handleInput(String input) {

    }

    /**
     * Adds the given quantity of an ingredient to the inventory.
     * If the ingredient is not the inventory, a new key is created for it.
     * @param ingredient The ingredient to be added
     * @param quantity The quantity of the ingredient to be added
     */
    private static void addToInventory(String ingredient, Integer quantity) {
        if (inventory.containsKey(ingredient)) {
            InventoryItem inventoryItem = inventory.get(ingredient);
            int currQuantity = inventoryItem.getQuantity();
            inventory.get(ingredient).setQuantity(currQuantity + quantity);
        }
        else {
            inventory.put(ingredient, new InventoryItem(quantity));
        }
    }

    /**
     * Removes the given quantity of an ingredient to the inventory.
     * @param ingredient The ingredient to be added
     * @param quantity The quantity of the ingredient to be added
     */
    private static void removeFromInventory(String ingredient, Integer quantity) {
        if (inventory.containsKey(ingredient) && inventory.get(ingredient).getQuantity() >=quantity) {
            addToInventory(ingredient, -quantity);
        }
    }

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
    public static String printInventory() {
        ArrayList<String> inventoryItems = new ArrayList<String>();
        for (Object key: inventory.keySet()) {
            InventoryItem inventoryItem = inventory.get(key);
            inventoryItems.add(key + ": " + System.lineSeparator() +
                    "\t quantity: " + inventoryItem.getQuantity() + System.lineSeparator() +
                    "\t threshold: " + inventoryItem.getThreshold()
            );
        }
        return "INVENTORY: " + System.lineSeparator() + String.join(System.lineSeparator(), inventoryItems);
    }

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
