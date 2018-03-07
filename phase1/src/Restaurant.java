import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Restaurant {
    // A HashMap representation of the inventory
    private static HashMap<String, InventoryItem> inventory;

    // A HashMap representation of the menu
    private static HashMap<String, MenuItem> menu;

    // An Array of the Waiters' names
    private static String[] waiterNameList;

    private static Type menuType = new TypeToken<HashMap<String,MenuItem>>() {}.getType();
    private static Type inventoryType = new TypeToken<HashMap<String,InventoryItem>>() {}.getType();

    // A list of all objects that can listen to events
    private static HashMap<String,Listener> listenerList = new HashMap<>();

    // An attribute to keep track of whether or not this Restaurant is running
    private static boolean running = true;

    // Keeps track of whether or not menu was modified since start
    private static boolean menuModified;
    // Keeps track of whether or not inventory was modified since start
    private static boolean inventoryModified;
    // Keeps track of whether or not waiterList was modified since start
    private static boolean waiterListModified;


    public static void main(String[] args) throws IOException {
        /* Creation of individual Files is currently commented out in case they need to be created and formatted later.
        File eventsFile = new File("events.txt");
        File requestsFile = new File("requests.txt");

        File menuFile = new File("menu.json");
        File inventoryFile = new File("inventory.json");*/

        String[] fileNames = {"events.txt", "requests.txt", "menu.json", "waiters.txt", "inventory.json"};
        for (String fileName : fileNames) {
            File file = new File(fileName);
            if (!file.isFile()) {
                file.createNewFile();
            }
        }

        Gson gson = new Gson();

        menu = gson.fromJson(new FileReader("menu.json"), menuType);
        inventory = gson.fromJson(new FileReader("inventory.json"), inventoryType);

        // Initialize all of the waiters on waiters.txt and add them to waitersList
        BufferedReader reader = new BufferedReader(new FileReader("waiters.txt"));
        waiterNameList = reader.readLine().split(",[ ]?");
        for (String waiterName : waiterNameList) listenerList.put("Waiter " + waiterName, new Waiter(waiterName));

        // Adds an instance of Kitchen to be used
        listenerList.put("Kitchen", new Kitchen());

        System.out.println("Inventory: " + inventory);
        System.out.println("Menu: " + menu);

        System.out.println(printInventory());

        run();
        System.out.println("Saving...");
        save();
        System.out.println("Stopping...");
    }

    /**
     * Handles the input from a new event in event.txt.
     * This sends the input to be handled to the appropriate Class or Object.
     * The input is separated by pipe symbols, and is appropriately spliced.
     * @param input The event input
     */
    private static void handleInput(String input) {
        String[] inputArray = input.split("(|)?");

        switch (inputArray[0]) {
            case "Restaurant":
                if (inputArray[1].equals("addToInventory")) {
                    // Precondition: inputArray[2] is an ingredient, inputArray[3] is a quantity
                    addToInventory(inputArray[2], Integer.valueOf(inputArray[3]));
                }
                break;
            case "Stop":
                running = false;
                break;
            default:
                Listener calledListener = listenerList.get(inputArray[0]);
                // Calls the concerned Listener's handleEvent method
                calledListener.handleEvent(Arrays.copyOfRange(inputArray, 1, inputArray.length - 1));
                break;
        }
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

        inventoryModified = true;
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
    private static String printInventory() {
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
    private void writeRequest(String item) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("requests.txt"));
        writer.write(item.toUpperCase() + " is needed in 20 quantities.");
        writer.newLine();
        writer.close();
    }

    /**
     * This reads through events.txt and handles input when a new line is added to the file.
     */
    private static void run() {
        File eventsFile = new File("events.txt");
        long lastModified = eventsFile.lastModified();
        String savedLastLine = "";
        while (running) {
            if (eventsFile.lastModified() != lastModified) {
                String lastLine = "";
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("events.txt"));


                    String currLine = reader.readLine();
                    while (currLine != null) {
                        lastLine = currLine;
                        currLine = reader.readLine();
                    }
                    reader.close();
                    System.out.println(lastLine);

                }
                catch (IOException e){
                    System.out.println("events.txt is busy, looping again");
                }
                catch (NullPointerException ignore) {}

                if (!lastLine.equals(savedLastLine)) {
                    lastModified = eventsFile.lastModified();
                    savedLastLine = lastLine;
                    handleInput(lastLine);
                }
            }

        }
    }

    /**
     * Saves the current state of menu, inventory, and waiters to their respective .json files.
     * Flushes events.txt
     */
    private static void save() throws IOException {
        Gson gson = new Gson();

        if (inventoryModified) {
            FileWriter writer = new FileWriter("inventory.json");
            gson.toJson(inventory, writer);
            writer.close();
        }

        if(menuModified) {
            FileWriter writer = new FileWriter("menu.json");
            gson.toJson(menu, writer);
            writer.close();
        }

        if(waiterListModified) {
            FileWriter writer = new FileWriter("waiters.txt");
            writer.write(String.join(",", waiterNameList));
            writer.close();
        }

        FileWriter eventsFile = new FileWriter("events.txt");
        eventsFile.flush();
        eventsFile.close();
    }
}
