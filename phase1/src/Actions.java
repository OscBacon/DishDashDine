import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/**
 * This class makes simulating input much easier by writing lines to events.txt according to the specified template.
 */
public abstract class Actions {
    /**
     * Adapted from http://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java on March 7th, 2018, 8:05 AM.
     * <p>
     * This function writes the input String s to the file Events.txt.
     *
     * @param s The string that is to written to events.txt.
     */
    private void eventWriter(String s) {
        try {
            FileWriter writer = new FileWriter("events.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            bufferedWriter.write(s);
            bufferedWriter.newLine();

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Kitchen inputs:

    /**
     * Writes to events.txt, simulating the input of a cook confirming that he has seen the dish order.
     *
     * @param cookName The name of the cook who has confirmed the dish.
     * @param dishID   The id of the dish that has just been confirmed.
     */
    public void acceptDish(String cookName, String dishID) {
        eventWriter("Kitchen | " + cookName + " | has accepted dish | " + dishID);
    }

    /**
     * Writes to events.txt, simulating the input of a cook letting the server know that a dish is ready for serving.
     *
     * @param dishID The id of the dish that is to be picked-up.
     */
    public void finishDish(String dishID) {
        eventWriter("Kitchen | Dish | " + dishID + " | is ready.");
    }


    // Server inputs:

    /**
     * Writes to events.txt, simulating the input of a waiter asking to see a certain bill.
     *
     * @param waiter     The waiter that wants to see a bill.
     * @param billNumber The bill this waiter wishes to see.
     */
    public void requestBill(String waiter, String billNumber) {
        eventWriter(waiter + " | requested bill | " + billNumber);
    }

    /**
     * Writes to events.txt, simulating the input of a server letting the kitchen know that a dish has been cancelled.
     *
     * @param dishID The id of the dish that has been cancelled.
     */
    public void cancelDish(String dishID) {
        eventWriter("Kitchen | Dish | " + dishID + " | cancelled.");
    }

    /**
     * Writes to events.txt, simulating the input of a waiter placing an order of a menu item with no substitutions for a certain table.
     *
     * @param waiter   The waiter who places the order.
     * @param itemName The name of the menu item that is being ordered.
     * @param tableNumber The number of the table this dished has been ordered from.
     */
    public void orderDish(String waiter, String itemName, String tableNumber) {
        eventWriter(waiter + " | ordered | " + itemName + " | for table | " + tableNumber);
    }

    /**
     * Writes to events.txt, simulating the input of a waiter placing an order of a menu item with substitutions for a certain table.
     *
     * @param waiter       The waiter who places the order.
     * @param itemName     The name of the menu item that is being ordered.
     * @param additions    The ingredients that must be added to the ordered item.
     * @param subtractions The ingredients that must be removed from the ordered item.
     * @param tableNumber The number of the table this dished has been ordered from.
     */
    // Additions and/or subtractions can be empty strings.
    public void orderDish(String waiter, String itemName, String additions, String subtractions, String tableNumber) {
        eventWriter(waiter + " | ordered | " + itemName + " | " + additions + " | " + subtractions + " | for table | " + tableNumber);
    }

    /**
     * Writes to events.txt, simulating the input of waiter confirming that a dish has been delivered to its table.
     *
     * @param waiter THe waiter who's serving the table and the dish.
     * @param dishID The id of the dish that has been served.
     */
    public void confirmDelivery(String waiter, String dishID) {
        eventWriter(waiter + " | delivered dish | " + dishID);
    }

    /**
     * Writes to events.txt, simulating the input of a waiter letting the kitchen know that a client recalled a dish.
     *
     * @param waiter The waiter whose clients recalled the dish.
     * @param dishID The id of the dish that was recalled.
     */
    public void dishRecall(String waiter, String dishID) {
        eventWriter(waiter + " | recalled dish | " + dishID);
    }


    // Receiver inputs:

    /**
     * Writes to events.txt, simulating the input of a receiver inputting the arrival of some ingredient.
     *
     * @param ingredient The ingredient that has arrived.
     * @param quantity   The quantity of this ingredient that has arrived.
     */
    public void addToInventory(String ingredient, String quantity) {
        eventWriter("Restaurant | addToInventory | " + ingredient + " | " + quantity);
    }

}
