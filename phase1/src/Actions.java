import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


/*
    This class makes simulating input much easier by writing lines to events.txt according to the specified template.
 */
public abstract class Actions
{
    /**
     * Adapted from http://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java on March 7th, 2018, 8:05 AM.
     *
     * This function writes the input String s to the file Events.txt.
     *
     * @param s The string that is to written to events.txt.
     */
    private void eventWriter(String s)
    {
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


    public void acceptDish(String cookName, String dishID)
    {
        eventWriter("Kitchen | " + cookName + " | has accepted dish | " + dishID);
    }


    public void finishDish(String dishID)
    {
        eventWriter("Kitchen | Dish | " + dishID + " | is ready.");
    }


    // Server inputs:


    public void requestBill(String waiter, String billNumber)
    {
        eventWriter(waiter + " | requested bill | " + billNumber);
    }


    public void cancelDish(String dishID)
    {
        eventWriter("Kitchen | Dish | " + dishID + " | cancelled.");
    }


    public void orderDish(String waiter, String itemName)
    {
        eventWriter(waiter + " | ordered | " + itemName);
    }


    public void orderDish(String waiter, String itemName, String additions, String subtractions)
    {
        eventWriter(waiter + " | ordered | " + itemName + " | " + additions + " | " + subtractions);
    }


    public void confirmDelivery(String waiter, String dishID)
    {
        eventWriter(waiter + " | delivered dish | " + dishID);
    }


    public void dishRecall(String waiter, String dishID)
    {
        eventWriter(waiter + " | recalled dish | " + dishID);
    }


    // Receiver inputs:


    public void addToInventory(String ingredient, String quantity)
    {
        eventWriter("Restaurant | addToInventory | " + ingredient + " | " + quantity);
    }

}
