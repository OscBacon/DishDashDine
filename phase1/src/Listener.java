public abstract class Listener {
    private String name;

    /**
     * Responds to input by calling appropriate methods with given parameters if there are any.
     *
     * @param inputArray the input to be handled, split into an array
     */
    public abstract void handleEvent(String[] inputArray);

    /**
     * Simulates printing information to a specific Object's "Screen" by adding the target's name to println.
     *
     * @param s String to be printed
     */
    public void printToScreen(String s) {
        System.out.println(name + ": " + s);
    }
}
