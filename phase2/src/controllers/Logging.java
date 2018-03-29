package controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 */
public abstract class Logging {
    /**
     * Adapted from http://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java on March 7th, 2018, 8:05 AM.
     *
     * Precondition: log.txt exists.
     */
    private static void eventWriter(String s) {
        try {
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(s);
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


// --------------------------------------------------------------------------------
    // Message:

    /**
     * @param user The user this message is dedicated to.
     * @param message The String that is to be displayed to the user.
     */
    public static void message(String user, String message) {
        eventWriter("Message | " + user + " | " + message);
    }


// --------------------------------------------------------------------------------
    // Kitchen inputs:

    /**
     *
     * @param cookName The name of the cook who has confirmed the dish.
     */
    public static void acceptDish(String cookName) {
        eventWriter("Kitchen | " + cookName + " | has accepted oldest dish.");
    }


    /**
     *
     * @param dishID The id of the dish that is to be picked-up.
     */
    public static void finishDish(String dishID) {
        eventWriter("Kitchen | Dish | " + dishID + " | is ready.");
    }


    public static void hasNewDish() {
        eventWriter("Kitchen | has a new dish.");
    }


// --------------------------------------------------------------------------------
    // Waiter inputs:

    /**
     *
     * @param waiter     The waiter that wants to see a bill.
     * @param tableNumber The bill this waiter wishes to see.
     */
    public static void requestActiveBill(String waiter, String tableNumber) {
        eventWriter("Waiter " + waiter + " | requested bill for table | " + tableNumber);
    }


    /**
     *
     * @param waiter     The waiter that wants to see a bill.
     * @param billNumber The bill this waiter wishes to see.
     */
    public static void requestBill(String waiter, String billNumber) {
        eventWriter("Waiter " + waiter + " | requested bill | " + billNumber);
    }


    /**
     *
     * @param dishID The id of the dish that has been cancelled.
     */
    public static void cancelDish(String waiter, String dishID) {
        eventWriter("Waiter " + waiter + " | cancelled dish | " + dishID);
    }


    /**
     *
     * @param waiter   The waiter who places the order.
     * @param itemName The name of the menu item that is being ordered.
     * @param tableNumber The number of the table this dished has been ordered from.
     */
    public static void orderDish(String waiter, String itemName, String tableNumber, String person) {
        eventWriter("Waiter " + waiter + " | ordered | " + itemName + " | for table | " + tableNumber + " | Person: " + person);
    }


    /**
     *
     * @param waiter       The waiter who places the order.
     * @param itemName     The name of the menu item that is being ordered.
     * @param additions    The ingredients that must be added to the ordered item.
     * @param subtractions The ingredients that must be removed from the ordered item.
     * @param tableNumber The number of the table this dished has been ordered from.
     */
    // Additions and/or subtractions can be empty strings.
    public static void orderDish(String waiter, String itemName, String additions, String subtractions, String tableNumber, String person) {
        eventWriter("Waiter " + waiter + " | ordered | " + itemName + " | " + additions + " | " + subtractions + " | for table | "
                + tableNumber + " | Person: " + person);
    }


    /**
     *
     * @param waiter The waiter who's serving the table and the dish.
     * @param dishID The id of the dish that has been served.
     */
    public static void confirmDelivery(String waiter, String dishID) {
        eventWriter("Waiter " + waiter + " | delivered dish | " + dishID);
    }


    /**
     *
     * @param waiter The waiter whose clients recalled the dish.
     * @param dishID The id of the dish that was recalled.
     */
    public static void dishRecall(String waiter, String dishID) {
        eventWriter("Waiter " + waiter + " | recalled dish | " + dishID);
    }


    /**
     *
     * @param waiter The waiter who starts the bill.
     * @param tableNumber The number of the table that this waiter is serving.
     */
    public static void newBill(String waiter, String tableNumber)
    {
        eventWriter("Waiter " + waiter + " | new bill | " + tableNumber);
    }


    /**
     *
     * @param waiter The waiter who starts this bill for 8 or more people.
     * @param tableNumber The number of the table that this waiter is serving.
     */
    public static void newLargeBill(String waiter, String tableNumber)
    {
        eventWriter("Waiter " + waiter + " | new large bill | " + tableNumber);
    }


    /**
     *
     * @param waiter The waiter whose bill gets paid.
     * @param tableNumber The number of the table that pays for its bill.
     */
    public static void  payBill(String waiter, String tableNumber)
    {
        eventWriter("Waiter " + waiter + " | pay bill | " + tableNumber);
    }


    /**
     * when the customer is very unsatisfied with the dish.
     *
     * @param waiter The waiter who is responsible fot this dish.
     * @param dishID The ID of the dish that dissatisfied the customer.
     */
    public static void removeDish(String waiter, String dishID)
    {
        eventWriter("Waiter " + waiter + " | removed dish | " + dishID);
    }


    /**
     *
     * @param split True if the bill is to be split.
     * @param billID The ID of the bill that is to be split or not.
     */
    public static void splitBill(boolean split, String billID) {
        if(split) {
            eventWriter("Waiter | wishes to split bill | " + billID);
        }
        else {
            eventWriter("Waiter | wishes to not split bill | " + billID);
        }
    }

// --------------------------------------------------------------------------------
    // Receiver inputs:

    /**
     *
     * @param ingredient The ingredient that has arrived.
     * @param quantity   The quantity of this ingredient that has arrived.
     */
    public static void addToInventory(String ingredient, String quantity) {
        eventWriter("Restaurant | add to inventory | " + ingredient + " | " + quantity);
    }


// --------------------------------------------------------------------------------
    // Manager inputs:

    /**
     *
     * @param waiterName The name of the waiter the manager is adding into the system.
     */
    public static void addWaiter(String waiterName) {
        eventWriter("Manager | add waiter | " + waiterName);
    }


    /**
     *
     * @param waiterName The name of the waiter the manager is removing from the system.
     */
    public static void removeWaiter(String waiterName) {
        eventWriter("Manager | remove waiter | " + waiterName);
    }

    /**
     *
     * @param cookName The name of the cook the manager is adding into the system.
     */
    public static void addCook(String cookName) {
        eventWriter("Manager | add cook | " + cookName);
    }


    /**
     *
     * @param cookName The name of the cook the manager is removing from the system.
     */
    public static void removeCook(String cookName) {
        eventWriter("Manager | remove cook | " + cookName);
    }


    public static void showUndeliveredDishes() {
        eventWriter("Manager | requested undelivered dishes.");
    }


    public static void showAllPayments() {
        eventWriter("Manager | requested all payments.");
    }

}
