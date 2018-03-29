package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.fxml.JavaFXBuilderFactory;
import models.InventoryItem;
import models.Listener;
import models.MenuItem;
import models.Kitchen;
import models.Waiter;
import models.Dish;
import models.Manager;
import models.Bill;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Restaurant extends Application {
    public static Stage stage;

    public static void setAlertedController(Alerted alertedController) {
        Restaurant.alertedController = alertedController;
    }

    public static Alerted alertedController;

    /**
     * A HashMap representation of the inventory.
     */
    private static HashMap<String, InventoryItem> inventory;

    /**
     * A HashMap representation of the menu.
     */
    private static HashMap<String, MenuItem> menu;

    /**
     * An Array of the Waiters' names.
     */
    private static ArrayList<String> waiterNameList;

    /**
     * An Array of the Cooks' names.
     */
    private static ArrayList<String> cookNameList;

    /**
     * The Type of menu.
     * This is used when parsing menu.json.
     */
    private static Type menuType = new TypeToken<HashMap<String, MenuItem>>() {
    }.getType();

    /**
     * The Type of inventory.
     * This is used when parsing inventory.json.
     */
    private static Type inventoryType = new TypeToken<HashMap<String, InventoryItem>>() {}.getType();

    /**
     * A list of all objects that can listen to log.txt.
     */
    private static HashMap<String, Listener> listenerList = new HashMap<>();

    /**
     * An attribute to keep track of whether or not this Restaurant is running.
     */
    private static boolean running = true;

    /**
     * This restaurant instance only reacts
     */
    private static String currentUser = "";

    /**
     * Keeps track of whether or not menu was modified since start.
     */
    private static boolean inventoryModified;
    /**
     * Keeps track of whether or not waiterList was modified since start.
     */
    private static boolean waiterListModified;

    /**
     * Keeps track of whether or not cookList was modified since start.
     */
    private static boolean cookListModified;

    /**
     * Keeps track of all dishes that have not yet been delivered.
     */
    private static HashMap<String, String> undeliveredDishes = new HashMap<>();

    /**
     * Keeps track of all payments that have been done to the restaurant today.
     */
    private static ArrayList<Bill> paidBills = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../resources/views/EmployeeSelection.fxml"));
        stage = primaryStage;
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) throws IOException {
        String[] fileNames = {"requests.txt", "menu.json", "waiters.txt", "cooks.txt", "inventory.json", "bills.json", "log.txt"};
        for (String fileName : fileNames) {
            File f = new File(fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
        }

        Gson gson = new Gson();

        menu = gson.fromJson(new FileReader("menu.json"), menuType);

        inventory = gson.fromJson(new FileReader("inventory.json"),inventoryType);
        if (inventory == null) {
            inventory = new HashMap<>();
        }
        else {
            for (String key: inventory.keySet()) {
                inventory.get(key).setName(key);
            }
        }
        // Checks inventory on launch to send out reorder requests ASAP
        checkInventory();

        // Initialize all of the waiters in waiters.txt and add them to waitersList
        try {
            BufferedReader reader = new BufferedReader(new FileReader("waiters.txt"));
            waiterNameList = new ArrayList<String>(Arrays.asList(reader.readLine().split(",[ ]?")));
            for (String waiterName : waiterNameList) {
                listenerList.put("Waiter " + waiterName, new Waiter(waiterName));
            }
        }
        catch (NullPointerException e) {
        }

        // Put all the names of all the cooks in cooks.txt and into cooksList
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cooks.txt"));
            cookNameList = new ArrayList<String>(Arrays.asList(reader.readLine().split(",[ ]?")));
        }
        catch (NullPointerException e) {
        }

        // Adds an instance of Kitchen to be used
        listenerList.put("Kitchen", new Kitchen());

        // Adds an instance of Manager to be used
        listenerList.put("Manager", new Manager());

        Thread inputHandlingThread = new Thread(Restaurant::run);

        inputHandlingThread.start();

        launch(args);


        save();
    }

    /**
     * Handles the input from a new event in log.txt.
     * This sends the input to be handled to the appropriate Class or Object.
     * The input is separated by pipe symbols, and is appropriately spliced.
     *
     * @param input The event input
     */
    private static void handleInput(String input) {
        String[] inputArray = input.split("( )?\\|( )?");

        for (int i = 0; i < inputArray.length; i++) // Protects from accidental spaces
        {
            inputArray[i] = inputArray[i].trim();
        }

        String in = inputArray[0];

        switch (in) {
            case "Restaurant":
                if (inputArray[1].equals("add to inventory")) {
                    // Precondition: inputArray[2] is an ingredient, inputArray[3] is a quantity
                    addToInventory(inputArray[2], Integer.valueOf(inputArray[3]));
                }
                break;
            case "Message":
                System.out.println(currentUser);
                if (inputArray[1].equals(currentUser)) {
                    printToScreen(inputArray[2]);
                }

                break;
            case "Stop":
                running = false;
                break;
            default:
                if (listenerList.get(in) != null) {
                    Listener calledListener = listenerList.get(in);
                    // Calls the concerned Listener's handleEvent method
                    calledListener.handleEvent(Arrays.copyOfRange(inputArray, 1, inputArray.length));
                }

                break;
        }
    }

    /**
     * Adds the given quantity of an ingredient to the inventory.
     * If the ingredient is not the inventory, a new key is created for it.
     *
     * @param ingredient The ingredient to be added
     * @param quantity   The quantity of the ingredient to be added
     */
    public static void addToInventory(String ingredient, Integer quantity) {
        if (inventory.containsKey(ingredient)) {
            InventoryItem inventoryItem = inventory.get(ingredient);

            int currQuantity = inventoryItem.getQuantity();

            if (currentUser.equals("Receiver")) {
                printToScreen("" + quantity + " units of " + ingredient + " were added to the inventory. " +
                        "There are now " + (currQuantity + quantity) + " units of " + ingredient + " in stock.");
            }

            inventory.get(ingredient).setQuantity(currQuantity + quantity);
        } else {
            inventory.put(ingredient, new InventoryItem(ingredient, quantity));
        }

        inventoryModified = true;


    }

    /**
     * Removes the given quantity of an ingredient to the inventory.
     *
     * @param ingredient The ingredient to be added
     * @param quantity   The quantity of the ingredient to be added
     */
    public static void removeFromInventory(String ingredient, Integer quantity) {
        if (inventory.containsKey(ingredient) && inventory.get(ingredient).getQuantity() >= quantity) {
            addToInventory(ingredient, -quantity);
        }
    }

    /**
     * Check the inventory to see if there are enough of each items.
     * If an inventory is under its threshold for reorder, a request for reorder is added to requests.txt
     */
    private static void checkInventory() {
        for (String key : inventory.keySet()) {
            InventoryItem item = inventory.get(key);
            if (item.getQuantity() < item.getThreshold()) {
                writeRequest(key);
            }
        }
    }

    /**
     * Checks in the inventory whether or not there are sufficient quantities of each item.
     * Tells which ingredients are in insufficient quantities
     *
     * @param ingredients The ingredients to be checked and the quantities needed
     * @return A String Array of all ingredients in insufficient quantities
     */
    public static ArrayList<String> checkIngredientsInventory(HashMap<String, Integer> ingredients) {
        ArrayList<String> insufficientIngredients = new ArrayList<>();
        for (String key : ingredients.keySet()) {
            if (inventory.containsKey(key)) {
                InventoryItem item = inventory.get(key);
                Integer quantityNeeded = ingredients.get(key);

                if (quantityNeeded > item.getQuantity()) {
                    insufficientIngredients.add(key);
                }
            } else {
                writeRequest(key);
                insufficientIngredients.add(key);
            }
        }

        return insufficientIngredients;
    }

    /**
     * Prints a string representation of the inventory.
     *
     * @return A string representation of the inventory
     */
    private static String printInventory() {
        ArrayList<String> inventoryItems = new ArrayList<String>();
        for (Object key : inventory.keySet()) {
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
     *
     * @param item The item to be requested
     */
    private static void writeRequest(String item) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("requests.txt", true));
            writer.write(item.toUpperCase() + " is needed in 20 quantities.");
            writer.newLine();
            writer.close();

            if (currentUser.equals("Manager")) {
                printToScreen("Wrote request to stock inventory with " + item);
            }
        } catch (IOException e) {}
    }

    /**
     * This reads through log.txt and handles input when a new line is added to the file.
     */
    private static void run() {
        File logFile = new File("log.txt");
        long lastModified = logFile.lastModified();
        while (running) {
            if (logFile.lastModified() != lastModified) {
                String lastLine = "";
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("log.txt"));
                    String currLine = reader.readLine();
                    while (currLine != null) {
                        if (!currLine.equals("")) {
                            lastLine = currLine;
                        }
                        currLine = reader.readLine();
                    }
                    reader.close();

                } catch (IOException | NullPointerException ignore) {

                }

                if (!lastLine.equals("")) {
                    lastModified = logFile.lastModified();
                    handleInput(lastLine);
                }
            }

        }
    }

    /**
     * Saves the current state of menu, inventory, and waiters to their respective .json files.
     * Flushes log.txt
     */
    private static void save() throws IOException {
        Gson gson = new Gson();

        if (inventoryModified) {
            FileWriter writer = new FileWriter("inventory.json");
            gson.toJson(inventory, writer);
            writer.close();
        }

        if (waiterListModified) {
            FileWriter writer = new FileWriter("waiters.txt");
            writer.write(String.join(", ", waiterNameList));
            writer.close();
        }

        if (cookListModified) {
            FileWriter writer = new FileWriter("cooks.txt");
            writer.write(String.join(", ", cookNameList));
            writer.close();
        }

        FileWriter logFile = new FileWriter("log.txt");
        logFile.flush();
        logFile.close();
    }

    /**
     * Getter for inventory.
     *
     * @return inventory
     */
    public static HashMap<String, InventoryItem> getInventory() {
        return inventory;
    }

    /**
     * Getter for menu.
     *
     * @return menu
     */
    public static HashMap<String, MenuItem> getMenu() {
        return menu;
    }

    /**
     * Getter for the waiterNameList
     * @return waiterNameList
     */
    public static ArrayList<String> getWaiterNameList() {
        return waiterNameList;
    }

    /**
     * Getter for the cookNameList
     * @return cookNameList
     */
    public static ArrayList<String> getCookNameList() {
        return cookNameList;
    }

    /**
     * Getter for Listener List
     * @return HashMap listenerList
     */
    public static HashMap<String, Listener> getListenerList() {
        return listenerList;
    }

    /**
     * Sets the currentUser attribute to the input parameter.
     * @param currentUser String of the currentUser's name.
     */
    public static void setCurrentUser(String currentUser) {
        Restaurant.currentUser = currentUser;
    }

    /**
     * Return the current user's name.
     * @return a String representing the current user's name.
     */
    public static String getCurrentUser() {
        return Restaurant.currentUser;
    }

    /**
     * Removes Dish dish from the HashMap of undeliveredDishes.
     * @param dish The Dish that has been delivered.
     */
    public static void removeFromUndeliveredDishes(Dish dish){
        undeliveredDishes.remove(Integer.toString(dish.getDishId()));
    }

    /**
     * Adds a dish to the HashMap of undeliveredDishes.
     * @param dish The Dish that has just been ordered.
     */
    public static void addToUndeliveredDishes(Dish dish){
        undeliveredDishes.put(Integer.toString(dish.getDishId()), dish.getName() + " " + Integer.toString(dish.getTableNumber()));
    }

    /**
     * Returns the HashMap of undeliveredDishes.
     * @return a HashMap of the undelivered dishes.
     */
    public static HashMap<String, String> getUndeliveredDishes(){
        return undeliveredDishes;
    }

    /**
     * Sets the value for waiterListModified.
     * @param waiterListModified boolean, true if waiterList has been modified since the start of the program.
     */
    public static void setWaiterListModified(boolean waiterListModified) {
        Restaurant.waiterListModified = waiterListModified;
    }

    /**
     * Sets the value for cookListModified.
     * @param cookListModified boolean, true if cookList has been modified since the start of the program.
     */
    public static void setCookListModified(boolean cookListModified) {
        Restaurant.cookListModified = cookListModified;
    }

    /**
     *  Adds the Bill bill to the ArrayList of paid bills.
     * @param bill A Bill that was just paid.
     */
    public static void addToPaidBills(Bill bill){
        paidBills.add(bill);
    }

    /**
     * A getter for the ArrayList of paid bills.
     * @return an ArrayList of Bill objects, each representing a bill that been paid for today.
     */
    public static ArrayList<Bill> getPaidBills(){
        return paidBills;
    }

    /**
     * Prints to the screen of the currentUser of this program.
     * @param message The String that shall be printed to the currentUser's screen.
     */
    private static void printToScreen(String message) {
        Platform.runLater(() -> alertedController.createAlert(message));
    }

    @Override
    public void stop() throws IOException {
        running = false;
    }
}