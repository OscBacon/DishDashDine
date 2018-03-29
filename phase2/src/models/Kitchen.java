package models;

import controllers.Logging;
import controllers.Restaurant;
import controllers.kitchen.MainController;
import javafx.application.Application;
import javafx.application.Platform;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Kitchen implements Listener {

    private static HashMap<String, Dish> dishList;  // This kitchen's list of active, accepted dishes

    public static LinkedHashMap<String, Dish> dishesToConfirm; // This kitchen's list of dishes that must be accepted


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
        System.out.println("Dish added!");

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

        if(dishesToConfirm.values().toArray().length > 0) {

            Dish dish = getFirstDish();   // Returns the oldest dish in dishesToConfirm

            String id = String.valueOf(dish.getDishId());

            dishList.put(id, dish);

            dish.setCook(cook);

            dishesToConfirm.remove(id);

            printToScreen("Cook " + cook + " has accepted " + dish.getName() + " (Dish id " + id + ")!");
        }
    }

    /**
     * Returns the next dish that must be confirmed by the kitchen.
     *
     * @return the Dish that must be acknowledged by the kitchen next.
     *
     * Precondition: dishesToConfirm has been verified to not be empty.
     */
    public static Dish getFirstDish() {
        return (Dish) dishesToConfirm.values().toArray()[0];
    }

    /**
     * Returns true if there are dishes to be confirmed in the kitchen.
     *
     * @return True if there are dishes to be confirmed in the kitchen.
     */
    public static boolean hasPendingDishes() {
        return dishesToConfirm.values().toArray().length > 0;
    }

    /**
     * Returns the Kitchen's list of active dishes.
     *
     * @return a Hashmap of the Kitchen's dishList.
     */
    public static HashMap<String, Dish> getDishList() {
        return dishList;
    }

    /**
     * Makes calls to the appropriate functions in this Kitchen class depending on the input array of Strings.
     *
     * @param inputArray The input to be handled, split into an array.
     */
    public void handleEvent(String[] inputArray) {
        if (inputArray[0].equals("has a new dish.")) {
            System.out.println("Kitchen has a new dish.");
            if (Restaurant.getCurrentUser().equals("Kitchen")) {
                printToScreen("new dish arrived!");
                Platform.runLater(() -> {
                    MainController controller = (MainController) Restaurant.alertedController;
                    controller.setPendingDishes();
                });
            }
        }
        if (inputArray.length >= 3) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
        {
            if (inputArray[2].equals("is ready."))
            {
                this.readyDish(inputArray[1]);
            }
        }

        else if (inputArray.length >= 2) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
        {
            if(inputArray[1].equals("has accepted oldest dish.")) {
                this.acceptDish(inputArray[0]);
            }
        }
    }

    public void printToScreen(String s) {
        if(Restaurant.getCurrentUser().trim().equals(("Kitchen").trim())) {
            Logging.message("Kitchen", s);
        }
    }
}
