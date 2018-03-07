import java.util.HashMap;

public class Kitchen extends Listener {

    private static HashMap<String, Dish> dishList;  // This kitchen's list of active dishes


    public Kitchen()
    {
        dishList = new HashMap<>();
    }

    /**
     * Adds the Dish object parameter to this kitchen's list of active dishes (dishList).
     * @param dish The dish that has just been ordered.
     */
    public static void addDish(Dish dish)
    {
        String id = String.valueOf(dish.getDishId());

        dishList.put(id, dish);
    }

    /**
     * Removes the Dish object parameter from this kitchen's list of active dishes (dishList).
     * @param dish The dish that is not to be prepared by the Kitchen anymore.
     */
    private static void removeDish(Dish dish)
    {
        String id = String.valueOf(dish.getDishId());

        dishList.remove(id);
    }

    /**
     * Informs the appropriate waiter that the dish whose id is the dishID parameter is ready for pick-up.
     * Then, calls removeDish() to remove the dish corresponding to dishID from this kitchen's dishList.
     *
     * @param dishID The id of the dish that has just been prepared by the kitchen.
     */
    private void readyDish(String dishID)
    {
        Dish dish = dishList.get(dishID);

        dish.getWaiter().printToScreen("Dish " + dish.getDishId() + " for table " +
                dish.getTableNumber() + " is ready for pick-up.");

        removeDish(dish);
    }

    /**
     * Cancels the dish by removing it from this kitchen's dishList.
     * Precondition: the dish has not yet been finished by the kitchen.
     *
     * @param dishID The id of the dish that is to be cancelled.
     */
    private void cancelDish(String dishID)
    {
        Dish dish = dishList.get(dishID);

        removeDish(dish);
    }

    /**
     * Makes calls to the appropriate functions in this Kitchen class depending on the input array of Strings.
     * @param inputArray The input to be handled, split into an array.
     */
    @Override
    public void handleEvent(String[] inputArray) {
        if (inputArray.length >= 3) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
        {
            if (inputArray[2].trim().equals("is ready."))
            {
                this.readyDish(inputArray[1]);
            }

            else if (inputArray[2].trim().equals("cancelled."))
            {
                this.cancelDish(inputArray[1]);
            }
        }
    }

    /**
     * Prints the input string s to this object's screen.
     * @param s String to be printed.
     */
    @Override
    public void printToScreen(String s) {
        super.printToScreen(s);
    }
}
