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


    public void acceptDish(String dishID)
    {

    }


    public void finishDish(String server, String dishID)
    {

    }


    // Server inputs:


    public void requestBill(String server, String billNumber)
    {

    }


    public void cancelDish(String server, String dishID)
    {

    }


    public void orderDish(String server, String dishID)
    {

    }


    public void confirmDelivery(String server, String dishID)
    {

    }


    public void dishRecall(String server, String dishID)
    {

    }


    // Receiver inputs:


    public void addToInventory(String ingredient, String quantity)
    {

    }

}
