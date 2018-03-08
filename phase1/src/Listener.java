public interface Listener {
    /**
     * Responds to input by calling appropriate methods with given parameters if there are any.
     *
     * @param inputArray the input to be handled, split into an array
     */
    public void handleEvent(String[] inputArray);

    /**
     * Simulates printing information to a specific Object's "Screen" by adding the target's name to println.
     *
     * @param s String to be printed
     */
    public void printToScreen(String s);
}
