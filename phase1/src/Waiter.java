
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Waiter implements Listener {
    /**
     * All unpaid, active Bills, accessible with the Bill's table number.
      */
    private HashMap<Integer, Bill> billList;

    /**
     * All the Bills that have ever been ordered through this waiter, accessible with the Bill id.
     * Includes paid and unpaid bills.
     */
    private HashMap<Integer,Bill> allBillsList;

    /**
     * All dishes ever ordered through this waiter.
      */
    private HashMap<Integer, Dish> dishList;

    /**
     * The name of this waiter.
     */
    private String name;


    public Waiter(String name) {
        this.name = name;
        this.billList = new HashMap<>();
        this.dishList = new HashMap<>();
        this.allBillsList = new HashMap<>();
    }


    /**
     * Returns the name of this waiter.
     *
     * @return This waiter's name as a String.
     */
    public String getName() {
        return name;
    }


    /**
     * Makes calls to the appropriate functions in this Kitchen class depending on the input array of Strings.
     *
     * @param inputArray The input to be handled, split into an array of Strings.
     */
    public void handleEvent(String[] inputArray) {

        if (inputArray.length >= 2) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
        {
            switch (inputArray[0]) {
                case "ordered": {
                    if (inputArray.length >= 6) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
                    {
                        // Create an array from the String of ingredients separated by commas
                        String[] additionsArray = inputArray[2].split(",");
                        String[] subtractionsArray = inputArray[3].split(",");

                        // Convert those arrays to ArrayLists to match with the necessary datatypes.
                        ArrayList<String> add = new ArrayList<>(Arrays.asList(additionsArray));
                        ArrayList<String> sub = new ArrayList<>(Arrays.asList(subtractionsArray));

                        this.createDish(inputArray[1], add, sub, inputArray[5]);
                    }

                    else if (inputArray.length >= 4) // Makes sure the inputArray is not erroneous to avoid an OutOfBounds exception.
                    {
                        this.createDish(inputArray[1], inputArray[3]);
                    }
                }
                    break;
                case "delivered dish":
                    this.confirmDishDelivery(Integer.valueOf(inputArray[1]));
                    break;
                case "recalled dish":
                    this.recallDish(Integer.valueOf(inputArray[1]));
                    break;
                case "new bill":
                    this.createBill(Integer.valueOf(inputArray[1]));
                    break;
                case "pay bill":
                    this.payBill(Integer.valueOf(inputArray[1]));
                    break;
                case "requested bill":
                    this.showBill(Integer.valueOf(inputArray[1]));
                    break;
                case "recall dish":
                    this.recallDish(Integer.valueOf(inputArray[1]));
                    break;
                case "remove dish":
                    this.removeDish(Integer.valueOf(inputArray[1]));
                    break;
            }
        }
    }


    /**
     * Helper for .createDish(), alters Ingredients based on additions and subtractions. Also makes sure all additions
     * and subtractions to this menu item are valid.
     *
     * @param menuItem The name of the menu item that has been ordered.
     * @param ingredients The ingredients and the quantities needed to make this menu item.
     * @param additions The ingredients that the client wished to add to the item.
     * @param subtractions The ingredients that the client wished to remove from the item.
     * @return True if all additions and subtractions to this menuItem are valid.
     */
    private boolean madeSubstitutions(MenuItem menuItem, HashMap<String, Integer> ingredients,
                                                       ArrayList<String> additions, ArrayList<String> subtractions) {
        for (String addition : additions) {
            if (!addition.equals("")) {
                if (menuItem.getAllowedAdditions().contains(addition)) {
                    if (ingredients.containsKey(addition)) {
                        ingredients.put(addition, ingredients.get(addition) + 1);
                    } else {
                        ingredients.put(addition, 1);
                    }
                } else {
                    this.printToScreen("Can't order: You can't add " + addition + ".");
                    return false;
                }
            }
        }
        // removing <subtractions> from <ingredients>
        for (String subtraction : subtractions) {
            if (!subtraction.equals("")) {
                if (menuItem.getAllowedSubtractions().contains(subtraction)) {
                    ingredients.remove(subtraction);
                } else {
                    this.printToScreen("Can't order: You can't remove " + subtraction + ".");
                    return false;
                }
            }
        }
        return true;
    }


    /**
     * Checks that the ordered dish is valid and that there are enough ingredients to fulfill the dish, and then places
     * the order by adding the constructed dish to the Kitchen's dishList and updating the restaurant's inventory.
     *
     * Precondition: <item> is on the menu.
     *
     * @param item The name of the item being ordered from the menu.
     * @param additions The ingredients the client wishes to add to the dish.
     * @param subtractions The ingredients the client wishes to remove from the dish.
     * @param tableNumber The number of the table that this dish is to be served to.
     */
    private void createDish(String item, ArrayList<String> additions, ArrayList<String> subtractions, String tableNumber) {
        MenuItem menuItem = Restaurant.getMenu().get(item);
        HashMap<String, Integer> ingredients = new HashMap<>(menuItem.getIngredients());

        // Attempt to create dish if substitutions were valid.
        if (madeSubstitutions(menuItem, ingredients, additions, subtractions)){

            ArrayList<String> missingIngredients = Restaurant.checkIngredientsInventory(ingredients);
            // There are sufficient ingredients, create the dish
            if (missingIngredients.isEmpty()) {
                Dish dish = new Dish(item, additions, subtractions, this, Integer.valueOf(tableNumber));
                dishList.put(dish.getDishId(), dish);
                Kitchen.addDish(dish);
                for (String ingredient : ingredients.keySet()) {
                    Integer quantity = ingredients.get(ingredient);
                    Restaurant.removeFromInventory(ingredient, quantity);
                }
                printToScreen(item + " (Dish id: " + dish.getDishId() + ") was ordered for Table " + tableNumber);
            }
            // There are insufficient ingredients to complete the order
            else {
                printToScreen("Can't order dish, insufficient ingredients: " + missingIngredients);
            }
        }
    }


    /**
     * Checks that there are enough ingredients to fulfill the ordered dish, and then places the order by adding the
     * constructed dish to the Kitchen's dishList and updating the restaurant's inventory.
     *
     * Precondition: <item> is on the menu.
     *
     * @param item The name of the item being ordered from the menu.
     * @param tableNumber The number of the table that this dish is to be served to.
     */
    private void createDish(String item, String tableNumber) {
        MenuItem menuItem = Restaurant.getMenu().get(item);
        HashMap<String, Integer> ingredients = new HashMap<>(menuItem.getIngredients());
        ArrayList<String> missingIngredients = Restaurant.checkIngredientsInventory(ingredients);
        if (missingIngredients.isEmpty()) {
            Dish dish = new Dish(item, this, Integer.valueOf(tableNumber));
            dishList.put(dish.getDishId(), dish);
            Kitchen.addDish(dish);
            for (String ingredient : ingredients.keySet()) {
                Integer quantity = ingredients.get(ingredient);
                Restaurant.removeFromInventory(ingredient, quantity);
            }
            printToScreen(item + " (Dish id: " + dish.getDishId() + ") was ordered for Table " + tableNumber + "!");
        } else {
            printToScreen("Can't order dish, insufficient ingredients: " + missingIngredients);
        }
    }


    /**
     * Creates a new bill in billList when this waiter starts serving a new table / arrival.
     *
     * @param tableNum The number of the table this bill is tied to.
     */
    private void createBill(int tableNum) {
        Bill bill = new Bill(tableNum, this);
        billList.put(tableNum, bill);                   // Add this bill to this waiter's active bills.
        allBillsList.put(bill.getBillID(), bill);       // Add this bill to all bills ever created for this waiter.
        printToScreen("New bill created for Table " + tableNum + "!");
    }


    /**
     * Pays the Bill of the given table, and removes this bill and its dishes from this waiter's lists of active
     * dishes and bills.
     *
     * @param tableNum Number of the table whose Bill is paid
     */
    private void payBill(int tableNum) {
        Bill bill = billList.get(tableNum); // Retrieve the bill object

        printToScreen(bill.toString()); // Print the bill to the screen

        for (Integer key : bill.getDishList().keySet()) {
            dishList.remove(key);   // Remove all dishes that were on this bill from this waiter's dishList.
        }

        billList.remove(tableNum); // Remove the bill from the active bills list

        printToScreen("Table " + tableNum + " has paid!");
    }


    /**
     * Adds the delivered dish to the table's bill.
     *
     * @param dishID The ID of the dish in question.
     */
    private void confirmDishDelivery(int dishID) {
        Dish dish = dishList.get(dishID);
        Bill bill = billList.get(dish.getTableNumber());
        bill.addDish(dish);
        printToScreen("Dish " + dishID + " delivered!");
    }


    /**
     * Removes this dish from its table's bill.
     *
     * @param dishID The ID of the dish in question.
     */
    private void removeDish(int dishID){
        Dish dish = dishList.get(dishID);
        Bill bill = billList.get(dish.getTableNumber());
        bill.removeDish(dishID);
        printToScreen("Dish " + dishID + " removed!");
    }


    /**
     * Removes this dish from the its table's bill and puts the dish back into the system (aka reorders the dish).
     *
     * @param dishID The ID of the dish in question.
     */
    private void recallDish(int dishID) {
        removeDish(dishID);
        Dish dish = dishList.get(dishID);
        createDish(dish.getName(), dish.getAdditions(), dish.getSubtractions(), String.valueOf(dish.getTableNumber()));
        printToScreen("Dish " + dishID + " recalled!");
    }


    /**
     * Finds the bill with billID in all of this waiter's bills and prints this bill.
     *
     * @param billID The ID of the bill that the waiter wishes to see.
     */
    private void showBill(int billID) {
        printToScreen(allBillsList.get(billID).toString());
    }


    /**
     * Returns a String representation of a waiter - the waiter's name.
     *
     * @return A String, the waiter's name.
     */
    public String toString() {
        return this.name;
    }


    /**
     * Method that standardizes the way we print messages to a waiter instance.
     *
     * @param s String to be printed.
     */
    public void printToScreen(String s) {
        System.out.println(name + ": " + s);
    }
}