package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Queue;

public class Kitchen implements Listener {

    private static HashMap<String, Dish> dishList;  // This kitchen's list of active, accepted dishes

    private static LinkedHashMap<String, Dish> dishesToConfirm; // This kitchen's list of dishes that must be accepted


    public Kitchen() {
        dishList = new HashMap<>();
        dishesToConfirm = new LinkedHashMap<>();
    }

    /**
     * Adds the Dish object parameter to this kitchen's list of active dishes (dishList).
     *
     * @param dish The dish that has just been ordered.
     */
    public static void addDish(Dish dish) {

        String id = String.valueOf(dish.getDishId());

        dishesToConfirm.put(id, dish);
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
     * This method allows to record which cook confirmed to cook the oldest dish.
     *
     * @param cook   The cook that will cook the dish whose ID is dishID.
     */
    private void acceptDish(String cook) {

        Dish dish = getFirstDish();   // Returns the oldest dish in dishesToConfirm

        String id = String.valueOf(dish.getDishId());

        dishList.put(id, dish);

        dish.setCook(cook);

        dishesToConfirm.remove(id);

        printToScreen("Cook " + cook + " has accepted " + dish.getName() + " (Dish id " + id + ")!");
    }

    /**
     * Returns the next dish that must be confirmed by the kitchen.
     *
     * @return the Dish that must be acknowledged by the kitchen next.
     */
    public Dish getFirstDish() {
        return (Dish) dishesToConfirm.keySet().toArray()[0];
    }

    /**
     * Makes calls to the appropriate functions in this Kitchen class depending on the input array of Strings.
     *
     * @param inputArray The input to be handled, split into an array.
     */
    public void handleEvent(String[] inputArray) {
        if (inputArray.length >= 3) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
        {
            if (inputArray[2].equals("is ready.")) {
                this.readyDish(inputArray[1]);
            } else if (inputArray[1].equals("has accepted oldest dish.")) {
                this.acceptDish(inputArray[0]);
            }
        }
    }

    public void printToScreen(String s) {
        System.out.println("Kitchen: " + s);
    }
}
