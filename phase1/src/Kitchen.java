import java.util.HashMap;

public class Kitchen implements Listener {

    private static HashMap<String, Dish> dishList;  // This kitchen's list of active dishes


    public Kitchen() {
        dishList = new HashMap<>();
    }

    /**
     * Adds the Dish object parameter to this kitchen's list of active dishes (dishList).
     *
     * @param dish The dish that has just been ordered.
     */
    public static void addDish(Dish dish) {
        String id = String.valueOf(dish.getDishId());

        dishList.put(id, dish);
    }

    /**
     * Removes the Dish object parameter from this kitchen's list of active dishes (dishList).
     *
     * @param dish The dish that is not to be prepared by the Kitchen anymore.
     */
    public static void removeDish(Dish dish) {
        String id = String.valueOf(dish.getDishId());

        dishList.remove(id);
    }

    /**
     * Informs the appropriate waiter that the dish whose id is the dishID parameter is ready for pick-up.
     * Then, calls removeDish() to remove the dish corresponding to dishID from this kitchen's dishList.
     *
     * @param dishID The id of the dish that has just been prepared by the kitchen.
     */
    private void readyDish(String dishID) {
        Dish dish = dishList.get(dishID);

        dish.getWaiter().printToScreen("Dish " + dish.getName() + " (Dish id " + dishID + ") for Table " +
                dish.getTableNumber() + " is ready for pick-up.");

        removeDish(dish);
    }

    /**
     * This method allows to record which cook confirmed to cook the dish whose id is dishID.
     *
     * @param cook   The cook that will cook the dish whose ID is dishID.
     * @param dishID The ID of the dish.
     */
    private void acceptDish(String cook, String dishID) {
        Dish dish = dishList.get(dishID);

        dish.setCook(cook);

        printToScreen("Cook " + cook + " has accepted " + dish.getName() + " (Dish id " + dishID + ")!");
    }

    /**
     * Makes calls to the appropriate functions in this Kitchen class depending on the input array of Strings.
     *
     * @param inputArray The input to be handled, split into an array.
     */
    @Override
    public void handleEvent(String[] inputArray) {
        if (inputArray.length >= 3) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
        {
            if (inputArray[2].equals("is ready.")) {
                this.readyDish(inputArray[1]);
            } else if (inputArray[1].equals("has accepted dish")) {
                this.acceptDish(inputArray[0], inputArray[2]);
            }
        }
    }

    public void printToScreen(String s) {
        System.out.println("Kitchen: " + s);
    }
}
